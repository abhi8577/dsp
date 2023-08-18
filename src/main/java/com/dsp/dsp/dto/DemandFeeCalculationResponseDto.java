package com.dsp.dsp.dto;

public class DemandFeeCalculationResponseDto {

	private Double superVisionAmount;
	
	private Double cgst;
	
	private Double sgst;
	
	private Double igst;
	
	private Double totalAmount;
	
	private String consumerApplicationNumber;
	
	private Double kwLoadAmount;
	
	private Double kvaLoadAmount;
	
	private Double estimatedAmount;

	public Double getSuperVisionAmount() {
		return superVisionAmount;
	}

	public void setSuperVisionAmount(Double superVisionAmount) {
		this.superVisionAmount = superVisionAmount;
	}

	public Double getCgst() {
		return cgst;
	}

	public void setCgst(Double cgst) {
		this.cgst = cgst;
	}

	public Double getSgst() {
		return sgst;
	}

	public void setSgst(Double sgst) {
		this.sgst = sgst;
	}

	public Double getIgst() {
		return igst;
	}

	public void setIgst(Double igst) {
		this.igst = igst;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getConsumerApplicationNumber() {
		return consumerApplicationNumber;
	}

	public Double getKwLoadAmount() {
		return kwLoadAmount;
	}

	public void setKwLoadAmount(Double kwLoadAmount) {
		this.kwLoadAmount = kwLoadAmount;
	}

	public Double getKvaLoadAmount() {
		return kvaLoadAmount;
	}

	public void setKvaLoadAmount(Double kvaLoadAmount) {
		this.kvaLoadAmount = kvaLoadAmount;
	}

	public void setConsumerApplicationNumber(String consumerApplicationNumber) {
		this.consumerApplicationNumber = consumerApplicationNumber;
	}

	public Double getEstimatedAmount() {
		return estimatedAmount;
	}

	public void setEstimatedAmount(Double estimatedAmount) {
		this.estimatedAmount = estimatedAmount;
	}
	
	
}