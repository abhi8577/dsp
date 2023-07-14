package com.dsp.dsp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="REGION")
public class Region {
	
	@Id
	@Column(name="REGION_CODE")
	private Long regionCode;
	
	@Column(name="REGION_NAME")
	private String regionName;	
	
	@Column(name="DISCOM_ID")
	private Long discomId;

	public Region() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(Long regionCode) {
		this.regionCode = regionCode;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public Long getDiscomId() {
		return discomId;
	}

	public void setDiscomId(Long discomId) {
		this.discomId = discomId;
	}
}