package com.dsp.dsp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="APPLICATION_REJECTED_DETAILS")
public class ApplicationRejectedDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SR_NO")
	private Long srNo;
	
	
	@Column(name = "REJECT_BY")
	private Long rejectBy;
	
	@Column(name = "REJECT_REMARK")
	private String rejectRemark;
	
	@Column(name = "APPLICATION_NO")
	private String applicationNo;
	
	@Column(name = "CREATION_DATE")
	private String creationDate;

	public Long getSrNo() {
		return srNo;
	}

	public void setSrNo(Long srNo) {
		this.srNo = srNo;
	}

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

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	
	
}
