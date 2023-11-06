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
	private Long buildingTypeId;

	@Column(name = "BUILDING_TYPE_NAME")
	private String buildingTypeName;

	public Long getBuildingTypeId() {
		return buildingTypeId;
	}

	public void setBuildingTypeId(Long buildingTypeId) {
		this.buildingTypeId = buildingTypeId;
	}

	public String getBuildingTypeName() {
		return buildingTypeName;
	}

	public void setBuildingTypeName(String buildingTypeName) {
		this.buildingTypeName = buildingTypeName;
	}

	

}
