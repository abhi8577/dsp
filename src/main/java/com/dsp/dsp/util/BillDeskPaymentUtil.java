package com.dsp.dsp.util;

import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.client.RestClientException;

import com.dsp.dsp.constant.BillDeskPaymentKeyConstant;
import com.dsp.dsp.constant.BillDeskPaymentValueConstant;
import com.dsp.dsp.dto.PaymentRequestDto;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;

public class BillDeskPaymentUtil {

	public static String encryptBillDeskPayloadProperties(PaymentRequestDto paymentRequestDto,HttpServletRequest request) throws JOSEException, RestClientException, JSONException, URISyntaxException {
		JSONObject params = new JSONObject();
		String orderDate = LocalDateTime.now().toString();
		int indexOf = LocalDateTime.now().toString().indexOf(".");
		orderDate = orderDate.substring(0, indexOf) + "+05:30";

		params.put(BillDeskPaymentKeyConstant.MERCHANT_KEY, BillDeskPaymentValueConstant.MERCHANT_KEY_VALUE);
		params.put(BillDeskPaymentKeyConstant.ORDER_ID,paymentRequestDto.getOrderId());
		params.put(BillDeskPaymentKeyConstant.AMOUNT, paymentRequestDto.getTotalAmount());
		params.put(BillDeskPaymentKeyConstant.ORDER_DATE, orderDate);
		params.put(BillDeskPaymentKeyConstant.CURRENCY, BillDeskPaymentValueConstant.CURRENCY_VALUE);
		params.put(BillDeskPaymentKeyConstant.RETURN_URL, BillDeskPaymentValueConstant.RETURN_URL_VALUE);

//		ADDITIONAL INFO
		JSONObject additionalInfo = new JSONObject();
		additionalInfo.put("additional_info1", paymentRequestDto.getConsumerApplicationNumber());
		additionalInfo.put("additional_info2", paymentRequestDto.getRegistrationAmount());
		additionalInfo.put("additional_info3", paymentRequestDto.getTotalAmount());
		additionalInfo.put("additional_info4", paymentRequestDto.getCgst());
		additionalInfo.put("additional_info5", paymentRequestDto.getSgst());
		additionalInfo.put("additional_info6", paymentRequestDto.getOrderId());
		additionalInfo.put("additional_info7", paymentRequestDto.getPaymentType());
		additionalInfo.put("additional_info8", paymentRequestDto.getDc());
		additionalInfo.put("additional_info9", paymentRequestDto.getDistrict());
		additionalInfo.put("additional_info10","NA");
		params.put(BillDeskPaymentKeyConstant.ADDITIONAL_INFO, additionalInfo);
		params.put(BillDeskPaymentKeyConstant.ITEM_CODE, BillDeskPaymentValueConstant.ITEM_CODE_VALUE);

//		INVOICE
		JSONObject invoice = new JSONObject();
		invoice.put(BillDeskPaymentKeyConstant.INVOICE_DISPLAY_NUMBER_KEY,
				String.valueOf(new Random().nextInt()).toString().replace("-", ""));
		invoice.put(BillDeskPaymentKeyConstant.INVOICE_NUMBER_KEY,
				"INDSP" + invoice.getString("invoice_display_number"));
		invoice.put(BillDeskPaymentKeyConstant.CUSTOMER_NAME_KEY, paymentRequestDto.getConsumerApplicationNumber());
		invoice.put(BillDeskPaymentKeyConstant.INVOICE_DATE_KEY, orderDate);

		params.put(BillDeskPaymentKeyConstant.INVOICE, invoice);

//		DEVICE
		JSONObject device = new JSONObject();
		device.put(BillDeskPaymentKeyConstant.INIT_CHANNEL, BillDeskPaymentValueConstant.INIT_CHANNEL_VALUE);
		device.put(BillDeskPaymentKeyConstant.IP_ADDRESS, Utility.getUserIPAddress(request));
		device.put(BillDeskPaymentKeyConstant.USER_AGENT, BillDeskPaymentValueConstant.USER_AGENT_VALUE);
		params.put(BillDeskPaymentKeyConstant.DEVICE, device);
		return encryptAndSignJWSWithHMAC(params.toString());
	}

	public static String encryptAndSignJWSWithHMAC(String reqStr) throws JOSEException {
		JWSSigner signer = new MACSigner(BillDeskPaymentValueConstant.SECRET_KEY_VALUE);
		HashMap<String, Object> customParams = new HashMap<String, Object>();
		customParams.put(BillDeskPaymentKeyConstant.CLIENT_ID, BillDeskPaymentValueConstant.CLIENT_ID_VALUE);
		@SuppressWarnings("deprecation")
		JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256, null, null, null, null, null, null, null, null, null,
				null, customParams, null);
		JWSObject jwsObject = new JWSObject(jwsHeader, new Payload(reqStr)); // Apply the HMAC
		jwsObject.sign(signer);
		return jwsObject.serialize();
	}

	public static String verifyAndDecryptJWSWithHMAC(String encryptedSignedMessage) throws Exception {
		JWSObject jwsObject = JWSObject.parse(encryptedSignedMessage);
		jwsObject.getHeader().getCustomParam("clientid").toString();
		JWSVerifier verifier = new MACVerifier(BillDeskPaymentValueConstant.SECRET_KEY_VALUE);
		jwsObject.verify(verifier);
		String message = jwsObject.getPayload().toString();
		return message;
	}
}