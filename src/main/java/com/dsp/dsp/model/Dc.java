package com.dsp.dsp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DC")
public class Dc {
	
	@Id
	@Column(name="DC_ID")
	private Long dcId;
	
	@Column(name="DC_CODE")
	private Long dcCode;
	
	@Column(name="DC_NAME")
	private String dcName;	
	
	@Column(name="DISTRICT_ID")
	private Long districtId;
	
	@Column(name="SUBDIV_ID")
	private Long subDivId;

	public Long getDcCode() {
		return dcCode;
	}

	public void setDcCode(Long dcCode) {
		this.dcCode = dcCode;
	}

	public String getDcName() {
		return dcName;
	}

	public void setDcName(String dcName) {
		this.dcName = dcName;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
 
	public Long getSubDivId() {
		return subDivId;
	}

	public void setSubDivId(Long subDivId) {
		this.subDivId = subDivId;
	}

	public Long getDcId() {
		return dcId;
	}

	public void setDcId(Long dcId) {
		this.dcId = dcId;
	}
}
