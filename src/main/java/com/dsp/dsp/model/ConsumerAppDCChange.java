package com.dsp.dsp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CONSUMER_APP_DC_CHANGE")
public class ConsumerAppDCChange {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="SR_NO")
	private Long srNo;
	
	@Column(name="CONSUMER_APPLICATION_ID")
	private String consumerApplicationId;
	
	@Column(name="OLD_DC_ID")
	private Long oldDcId;
	
	@Column(name="NEW_DC_ID")
	private Long newDcId;
	
	@Column(name="REASON")
	private String reason;
	
	@Column(name="UPDATED_BY")
	private String updatedBy;
	
	@Column(name="UPDATED_TIME")
	private String updatedTime;

	public ConsumerAppDCChange() {
		super();
	}

	public Long getSrNo() {
		return srNo;
	}

	public void setSrNo(Long srNo) {
		this.srNo = srNo;
	}

	public String getConsumerApplicationId() {
		return consumerApplicationId;
	}

	public void setConsumerApplicationId(String consumerApplicationId) {
		this.consumerApplicationId = consumerApplicationId;
	}

	public Long getOldDcId() {
		return oldDcId;
	}

	public void setOldDcId(Long oldDcId) {
		this.oldDcId = oldDcId;
	}

	public Long getNewDcId() {
		return newDcId;
	}

	public void setNewDcId(Long newDcId) {
		this.newDcId = newDcId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}
}