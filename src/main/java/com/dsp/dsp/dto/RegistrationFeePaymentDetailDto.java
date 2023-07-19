package com.dsp.dsp.dto;

public class RegistrationFeePaymentDetailDto {
	
	private String consumerApplicationId;
	
	private String consumerName;
	
	private String email;
	
	private String mobileNo;
	
	private String paymentParticular;
	
	private String orderId;
	
	private String fees;

	public String getConsumerApplicationId() {
		return consumerApplicationId;
	}

	public void setConsumerApplicationId(String consumerApplicationId) {
		this.consumerApplicationId = consumerApplicationId;
	}

	public String getConsumerName() {
		return consumerName;
	}

	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPaymentParticular() {
		return paymentParticular;
	}

	public void setPaymentParticular(String paymentParticular) {
		this.paymentParticular = paymentParticular;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getFees() {
		return fees;
	}

	public void setFees(String fees) {
		this.fees = fees;
	}
}