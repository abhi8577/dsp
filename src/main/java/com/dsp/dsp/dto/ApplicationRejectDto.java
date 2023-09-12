package com.dsp.dsp.dto;

import javax.persistence.Column;

public class ApplicationRejectDto {

	
	private Long rejectBy;
	
	private String rejectRemark;
	
	private String applicationNo;

	public Long getRejectBy() {
		return rejectBy;
	}

	public void setRejectBy(Long rejectBy) {
		this.rejectBy = rejectBy;
	}

	public String getRejectRemark() {
		return rejectRemark;
	}

	public void setRejectRemark(String rejectRemark) {
		this.rejectRemark = rejectRemark;
	}

	public String getApplicationNo() {
		return applicationNo;
	}

	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}
	
	
}
