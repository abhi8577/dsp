package com.dsp.dsp.constant;

public interface BillDeskPaymentValueConstant {

	/**** TESTING ****/
	/* INITIAL PAYMENT TEST KIT */

	String BASE_URL = "https://pguat.billdesk.io/payments/ve1_2/orders/create";
	// String
	// RETURN_URL_VALUE="https://survey.mpcz.in:8080/ssp-web/payment/response"; //
	String RETURN_URL_VALUE = "http://localhost:8080/dsp/payment/billdesk_payment_response";

	/* REFUND PROPERTIES TEST KIT */
	String REFUND_URL = "https://pguat.billdesk.io/payments/ve1_2/refunds/create";

	/* RETRIEVE TEST KIT TRANSACTION */
	String RETRIEVE_TRX_URL = "https://pguat.billdesk.io/payments/ve1_2/transactions/get";

	/* NSC MERCHANT VALUE FOR TEST KIT */
	String MERCHANT_KEY_VALUE = "UATMPMKVV";
	String CLIENT_ID_VALUE = "uatmpmkvv";
	String SECRET_KEY_VALUE = "Z4aBcHyD9QS5KiGvYDVZxZxXFSex8TOA";

	/**** PRODUCTION ****/

	/* INITIAL PAYMENT PROD KIT */
	// String BASE_URL = "https://api.billdesk.com/payments/ve1_2/orders/create";
	// String RETURN_URL_VALUE =
	// "https://saralsanyojan.mpcz.in:8888/payment/response";

	/* REFUND PROPERTIES PROD KIT */
	// String REFUND_URL = "https://api.billdesk.com/payments/ve1_2/refunds/create";

	/* RETRIEVE REFUND PROD TRANSACTION */
	// String RETRIEVE_REFUND_URL =
	// "https://api.billdesk.com/payments/ve1_2/refunds/get";

	/* RETRIEVE PROD TRANSACTION */
	// RETRIEVE_TRX_URL =
	// "https://api.billdesk.com/payments/ve1_2/transactions/get";

	/* NSC MERCHANT VALUE FOR PROD KIT */
	// String MERCHANT_KEY_VALUE = "";
	// String CLIENT_ID_VALUE = "";
	// String SECRET_KEY_VALUE = "";

	/**** CONSTANT ****/
	// YamlValueConfig valueConfig = new YamlValueConfig();
	String ORDER_ID_VALUE = "DSPPYMNT";
	String CURRENCY_VALUE = "356";
	String ITEM_CODE_VALUE = "DIRECT";
	/* DEVICE VALUE */
	String IP_ADDRESS_VALUE = "172.16.17.20";
	String MAC_VALUE = "74-E5-F9-56-8B-0A";
	String INIT_CHANNEL_VALUE = "internet";
	String IMEI_VALUE = "990000112233445";
	String FINGER_PRINT_ID_VALUE = "61b12c18b5d0cf901be34a23ca64bb19";
	String USER_AGENT_VALUE = "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:51.0) Gecko/20100101 Firefox/51.0";
	String ACCEPT_HEADER_VALUE = "text/html";

	/* HEADER VALUE */
	String CONTENT_TYPE_VALUE = "application/jose";
	String ACCEPT_VALUE = "application/jose";
}
