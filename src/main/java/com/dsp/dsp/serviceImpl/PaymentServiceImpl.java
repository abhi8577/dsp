package com.dsp.dsp.serviceImpl;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dsp.dsp.constant.ResponseConstant;
import com.dsp.dsp.dto.PaymentRequestDto;
import com.dsp.dsp.model.ConsumerApplication;
import com.dsp.dsp.model.DspInvoiceHistory;
import com.dsp.dsp.model.DspPaymentHistory;
import com.dsp.dsp.repository.ConsumerApplicationRepository;
import com.dsp.dsp.repository.ConsumerRepository;
import com.dsp.dsp.repository.DspInvoiceHistoryRepository;
import com.dsp.dsp.repository.DspPaymentHistoryRepository;
import com.dsp.dsp.response.Response;
import com.dsp.dsp.service.PaymentService;
import com.dsp.dsp.util.BillDeskPaymentUtil;
import com.dsp.dsp.util.BillDeskUtil;
import com.dsp.dsp.util.Utility;
import com.nimbusds.jose.shaded.gson.Gson;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	ConsumerApplicationRepository consumerApplicationRepository;

	@Autowired
	DspInvoiceHistoryRepository dspInvoiceHistoryRepository;

	@Autowired
	DspPaymentHistoryRepository dspPaymentHistoryRepository;

	@Autowired
	ConsumerRepository consumerRepository;

	@Override
	public Response createOrder(String consumerAppNo, String paymentType, HttpServletRequest httpServletRequest) {

		Map<Object, Object> response = new HashMap<>();
		try {
			
			ConsumerApplication consumerApplication = consumerApplicationRepository
					.findByConsumerApplicationId(consumerAppNo);
			if (consumerApplication == null) {
				return Response.response("Consumer Application Not Found", HttpStatus.NOT_FOUND, null, null);
			}
			
			DspInvoiceHistory dspInvoiceHistory = dspInvoiceHistoryRepository
					.findByApplicationNumberAndPaymentType(consumerAppNo, paymentType);

			if (dspInvoiceHistory != null && dspInvoiceHistory.getTransaction_error_type().equalsIgnoreCase("success")
					&& dspInvoiceHistory.getAuth_status().equals("0300")) {
				return Response.response("Payment Already Done", HttpStatus.CONFLICT, null, null);
			} else {

				DspPaymentHistory dspPaymentHistory = dspPaymentHistoryRepository
						.findByApplicationNumberAndPaymentType(consumerAppNo, paymentType);

				int durationMinutes = 0;
				if (dspPaymentHistory != null) {
					OffsetDateTime oldDate = OffsetDateTime.parse(dspPaymentHistory.getOrderDate());
					OffsetDateTime currentDateTime = OffsetDateTime.now(ZoneOffset.ofHoursMinutes(5, 30));
					Duration duration = Duration.between(oldDate, currentDateTime);
					durationMinutes = (int) duration.toMinutes();
					System.err.println("Duration--- " + durationMinutes);
				}
				if (dspPaymentHistory == null || dspPaymentHistory.getPaymentStatus() == 1 && durationMinutes > 25) {

					if(dspPaymentHistory!=null) {
						dspPaymentHistoryRepository.delete(dspPaymentHistory);
					}
					PaymentRequestDto paymentRequestDto = new PaymentRequestDto();
					paymentRequestDto.setConsumerApplicationNumber(consumerAppNo);
					paymentRequestDto.setPaymentType(paymentType);
					paymentRequestDto.setOrderId("ORID" + Utility.getRandomNumber());
					paymentRequestDto.setRegistrationAmount(new BigDecimal(1000));
					paymentRequestDto.setCgst(new BigDecimal(90));
					paymentRequestDto.setSgst(new BigDecimal(90));
					paymentRequestDto.setTotalAmount(new BigDecimal(1180));
					paymentRequestDto.setDc("DC");
					paymentRequestDto.setDistrict("district");
					paymentRequestDto.setDemandAmount(new BigDecimal(0));

					response = BillDeskUtil.createPaymentOrder(paymentRequestDto, httpServletRequest);

					if (!response.isEmpty()) {
						if (response.get("DspPaymentHistory") != null) {
							DspPaymentHistory dspPaymentHistory1 = new DspPaymentHistory();
							BeanUtils.copyProperties(response.get("DspPaymentHistory"), dspPaymentHistory1);

							dspPaymentHistory1.setPaymentSource("BILLDESK");
							dspPaymentHistory1.setApplicationNumber(consumerApplication.getConsumerApplicationId());
							dspPaymentHistory1.setConsumerMobile(consumerRepository
									.findById(consumerApplication.getConsumerId()).get().getMobileNumber());
							dspPaymentHistory1.setConsumerName(consumerRepository
									.findById(consumerApplication.getConsumerId()).get().getConsumerName());
							dspPaymentHistory1.setTotalAmount(paymentRequestDto.getTotalAmount());
							dspPaymentHistory1.setCgst(paymentRequestDto.getCgst());
							dspPaymentHistory1.setSgst(paymentRequestDto.getSgst());
							dspPaymentHistoryRepository.save(dspPaymentHistory1);
							response.put(ResponseConstant.STATUS_CODE, HttpStatus.CREATED.value());
							response.put(ResponseConstant.MESSAGE, HttpStatus.CREATED);
							response.put("data", dspPaymentHistory1);

						} else {
							response.put(ResponseConstant.STATUS_CODE, response.get(ResponseConstant.STATUS_CODE));
							response.put(ResponseConstant.ERROR_MESSAGE, response.get(ResponseConstant.ERROR_MESSAGE));
						}

					} else {
						response.put(ResponseConstant.STATUS_CODE, HttpStatus.NOT_FOUND.value());
						response.put(ResponseConstant.ERROR_MESSAGE, "Payment Order Not Created");
					}

				} else {
					return Response.response("Order Already Created", HttpStatus.CONFLICT, dspPaymentHistory, null);

				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return Response.response("Payment Order Created Successfully", HttpStatus.OK, response, null);
	}

	@Override
	public Response savePaymentResponse(HttpServletRequest httpServletRequest) throws Exception {
		Map<String, String> map = new TreeMap<>();
		String response = null;

		try {
			Enumeration<String> parameterNames = httpServletRequest.getParameterNames();
			while (parameterNames.hasMoreElements()) {
				String paramName = parameterNames.nextElement();
				String[] paramValues = httpServletRequest.getParameterValues(paramName);
				map.put(paramName, paramValues[0]);
			}
			if (!map.isEmpty() && !map.get("transaction_response").isEmpty()) {

				DspInvoiceHistory dspInvoiceHistory = new DspInvoiceHistory();
				System.err.println("callback map:" + map);
				response = BillDeskPaymentUtil.verifyAndDecryptJWSWithHMAC(map.get("transaction_response").toString());
				dspInvoiceHistory = new Gson().fromJson(response, DspInvoiceHistory.class);
				System.err.println("callback decrypted map:" + response);

				DspPaymentHistory dspPaymentHistory = dspPaymentHistoryRepository
						.findByOrderId(dspInvoiceHistory.getOrderid());
				BeanUtils.copyProperties(dspPaymentHistory, dspInvoiceHistory);
				DspInvoiceHistory save = dspInvoiceHistoryRepository.save(dspInvoiceHistory);
				if (save != null && save.getTransaction_error_type().equalsIgnoreCase("success")
						&& save.getAuth_status().equals("0300")) {

					dspPaymentHistory.setPaymentStatus(2);
					dspPaymentHistory.setUpdatedDate(LocalDateTime.now().toString());
					dspPaymentHistory.setRemark(dspInvoiceHistory.getTransaction_error_desc());
					DspPaymentHistory paymentHistory = dspPaymentHistoryRepository.save(dspPaymentHistory);
					if (paymentHistory != null) {

						ConsumerApplication consumerApplication = consumerApplicationRepository
								.findByConsumerApplicationId(paymentHistory.getApplicationNumber());
						consumerApplication.setApplicationStatusId(6L);
						consumerApplicationRepository.save(consumerApplication);
					}
					return Response.response("Payment successfully Done", HttpStatus.OK, save, null);
				}
				else {
					return Response.response("Payment Failed", HttpStatus.BAD_GATEWAY, save, null);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.response("Payment Not Done", HttpStatus.CONFLICT, null, null);
	}
}