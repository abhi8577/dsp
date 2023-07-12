package com.dsp.dsp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="LAND_AREA_UNIT")
public class LandAreaUnit {
	
	@Id
	@Column(name="LAND_AREA_UNIT_ID")
	private Long landAreaUnitId;
	
	@Column(name="LAND_AREA_UNIT_NAME")
	private String landAreaUnitName;

	public Long getLandAreaUnitId() {
		return landAreaUnitId;
	}

	public void setLandAreaUnitId(Long landAreaUnitId) {
		this.landAreaUnitId = landAreaUnitId;
	}

	public String getLandAreaUnitName() {
		return landAreaUnitName;
	}

	public void setLandAreaUnitName(String landAreaUnitName) {
		this.landAreaUnitName = landAreaUnitName;
	}
	
	
	

}
