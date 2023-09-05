package com.dsp.dsp.util;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URI;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.dsp.dsp.constant.BillDeskPaymentKeyConstant;
import com.dsp.dsp.constant.BillDeskPaymentValueConstant;
import com.dsp.dsp.constant.ResponseConstant;
import com.dsp.dsp.dto.BillDeskOrderResponseDTO;
import com.dsp.dsp.dto.PaymentRequestDto;
import com.dsp.dsp.model.DspPaymentHistory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.shaded.gson.Gson;

public class BillDeskUtil {

	
	public static Map<Object, Object> createPaymentOrder(PaymentRequestDto paymentRequestDto,HttpServletRequest request) throws Exception {
		Map<Object, Object> map = new HashMap<>();
		HttpHeaders headers = new HttpHeaders();
		Long time = Timestamp.valueOf(LocalDateTime.now()).getTime();
		headers.set(BillDeskPaymentKeyConstant.CONTENT_TYPE_KEY, BillDeskPaymentValueConstant.CONTENT_TYPE_VALUE);
		headers.set(BillDeskPaymentKeyConstant.ACCEPT_KEY, BillDeskPaymentValueConstant.ACCEPT_VALUE);
		headers.set(BillDeskPaymentKeyConstant.BD_TIMESTAMP_KEY, time.toString());
		headers.set(BillDeskPaymentKeyConstant.BD_TRACEID_KEY, time.toString() + "DSP" + new Random().nextInt(10));

		String encryptedPayload = BillDeskPaymentUtil.encryptBillDeskPayloadProperties(paymentRequestDto,request);

		System.err.println("Header : " + new Gson().toJson(headers));
		System.err.println("Payload: " + BillDeskPaymentUtil.verifyAndDecryptJWSWithHMAC(encryptedPayload));

//      PROXY MPcZ
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy.mpcz.in", 8080));
		requestFactory.setProxy(proxy);
		RestTemplate restTemplate = new RestTemplate(requestFactory);

		HttpEntity<String> requestEntity = new HttpEntity<>(encryptedPayload, headers);
		ResponseEntity<String> responseEntity = null;
		BillDeskOrderResponseDTO billDeskOrderResponseDTO = null;
		String decryptedPayload = null;
		try {
			responseEntity = restTemplate.postForEntity(new URI(BillDeskPaymentValueConstant.BASE_URL), requestEntity,
					String.class);
			System.err.println("responseEntity-- "+responseEntity.getBody());
			if (responseEntity.getStatusCodeValue() == 200) {
				decryptedPayload = BillDeskPaymentUtil.verifyAndDecryptJWSWithHMAC(responseEntity.getBody());
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				billDeskOrderResponseDTO = objectMapper.readValue(decryptedPayload,
						new TypeReference<BillDeskOrderResponseDTO>() {
						});
				System.err.println("Response :" + new Gson().toJson(billDeskOrderResponseDTO));
				DspPaymentHistory nscPaymentHistory = new DspPaymentHistory();
				BeanUtils.copyProperties(billDeskOrderResponseDTO, nscPaymentHistory);
				JSONObject jsonObject = new JSONObject(
						BillDeskPaymentUtil.verifyAndDecryptJWSWithHMAC(encryptedPayload));
				nscPaymentHistory.setInvoiceId(jsonObject.getJSONObject("invoice").getString("invoice_number"));
				nscPaymentHistory.setPaymentStatus(1);
				nscPaymentHistory.setPaymentType(paymentRequestDto.getPaymentType());
				nscPaymentHistory.setBdOrderId(billDeskOrderResponseDTO.getBdorderid());
				nscPaymentHistory.setOrderId(billDeskOrderResponseDTO.getOrderid());
				nscPaymentHistory.setToken(billDeskOrderResponseDTO.getLinks().get(1).getHeaders().getAuthorization());
				nscPaymentHistory.setUpdatedDate(billDeskOrderResponseDTO.getCreatedon());
				nscPaymentHistory.setOrderDate(billDeskOrderResponseDTO.getOrder_date());
				nscPaymentHistory.setIpAddress(Utility.getUserIPAddress(request));
				map.put("DspPaymentHistory", nscPaymentHistory);
			}
			map.put("data", billDeskOrderResponseDTO);
			map.put("token", billDeskOrderResponseDTO.getLinks().get(1).getHeaders().getAuthorization());
		} catch (HttpClientErrorException e) {
			if (e.getStatusCode().value() == 401) {
				map.put(ResponseConstant.STATUS_CODE, HttpStatus.UNAUTHORIZED.value());
				map.put(ResponseConstant.ERROR_MESSAGE, HttpStatus.UNAUTHORIZED.getReasonPhrase());
			} else if (e.getStatusCode().value() == 422) {
				map.put(ResponseConstant.STATUS_CODE, "ORIDE0021");
				map.put(ResponseConstant.ERROR_MESSAGE, "invalid data error");
			} else if (e.getStatusCode().value() == 409) {
				map.put(ResponseConstant.STATUS_CODE, "GNDRE0001");
				map.put(ResponseConstant.ERROR_MESSAGE, "Duplicate request error");
			}
			e.printStackTrace();
		} catch (HttpServerErrorException e) {
			if (e.getStatusCode().value() == 502) {
				map.put(ResponseConstant.STATUS_CODE, HttpStatus.BAD_GATEWAY.value());
				map.put(ResponseConstant.ERROR_MESSAGE, HttpStatus.BAD_GATEWAY.getReasonPhrase());
			} else if (e.getStatusCode().value() == 500) {
				map.put(ResponseConstant.STATUS_CODE, HttpStatus.INTERNAL_SERVER_ERROR.value());
				map.put(ResponseConstant.ERROR_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;

	}
}
