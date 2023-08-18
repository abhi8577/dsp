package com.dsp.dsp.dto;

import java.math.BigDecimal;

public class PaymentRequestDto {
	
	private String consumerApplicationNumber;
	private String paymentType;
	private String orderId;
	private BigDecimal totalAmount;
	private BigDecimal registrationAmount;
	private BigDecimal demandAmount;
	private BigDecimal cgst;
	private BigDecimal sgst;
	private BigDecimal igst;
	private String dc;
	private String district;
	
	public PaymentRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getConsumerApplicationNumber() {
		return consumerApplicationNumber;
	}
	public void setConsumerApplicationNumber(String consumerApplicationNumber) {
		this.consumerApplicationNumber = consumerApplicationNumber;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	public BigDecimal getRegistrationAmount() {
		return registrationAmount;
	}
	public void setRegistrationAmount(BigDecimal registrationAmount) {
		this.registrationAmount = registrationAmount;
	}
	public BigDecimal getDemandAmount() {
		return demandAmount;
	}
	public void setDemandAmount(BigDecimal demandAmount) {
		this.demandAmount = demandAmount;
	}
	public BigDecimal getCgst() {
		return cgst;
	}
	public void setCgst(BigDecimal cgst) {
		this.cgst = cgst;
	}
	public BigDecimal getSgst() {
		return sgst;
	}
	public void setSgst(BigDecimal sgst) {
		this.sgst = sgst;
	}
	public String getDc() {
		return dc;
	}
	public void setDc(String dc) {
		this.dc = dc;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public BigDecimal getIgst() {
		return igst;
	}
	public void setIgst(BigDecimal igst) {
		this.igst = igst;
	}
}
