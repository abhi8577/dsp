package com.dsp.dsp.dto;

public class DcAcceptOrDcChangeDto {

	private String consumerApplicationNumber;
	
	private Boolean isDcChange;
	
	private Long newdDcId;
	
	private Long oldDcId;
	
	private String dcChangedReason;
	
	private String updatedBy;

	public DcAcceptOrDcChangeDto() {
		super();
	}

	public String getConsumerApplicationNumber() {
		return consumerApplicationNumber;
	}

	public void setConsumerApplicationNumber(String consumerApplicationNumber) {
		this.consumerApplicationNumber = consumerApplicationNumber;
	}

	public Boolean getIsDcChange() {
		return isDcChange;
	}

	public void setIsDcChange(Boolean isDcChange) {
		this.isDcChange = isDcChange;
	}

	public Long getNewdDcId() {
		return newdDcId;
	}

	public void setNewdDcId(Long newdDcId) {
		this.newdDcId = newdDcId;
	}

	public Long getOldDcId() {
		return oldDcId;
	}

	public void setOldDcId(Long oldDcId) {
		this.oldDcId = oldDcId;
	}

	public String getDcChangedReason() {
		return dcChangedReason;
	}

	public void setDcChangedReason(String dcChangedReason) {
		this.dcChangedReason = dcChangedReason;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
}