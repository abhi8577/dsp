package com.dsp.dsp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BUILDING_TYPE")
public class BuildingType {

	@Id
	@Column(name = "BUILDING_TYPE_ID")
	private Long applyTypeId;

	@Column(name = "BUILDING_TYPE_NAME")
	private String applyTypeName;

	public Long getApplyTypeId() {
		return applyTypeId;
	}

	public void setApplyTypeId(Long applyTypeId) {
		this.applyTypeId = applyTypeId;
	}

	public String getApplyTypeName() {
		return applyTypeName;
	}

	public void setApplyTypeName(String applyTypeName) {
		this.applyTypeName = applyTypeName;
	}

	public BuildingType() {
		super();
		// TODO Auto-generated constructor stub
	}

}
