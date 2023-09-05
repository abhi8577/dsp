package com.dsp.dsp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="LOAD_UNIT")
public class LoadUnit {
	
	@Id
	@Column(name="LOAD_UNIT_ID")
	private Long loadUnitId;
	
	@Column(name="LOAD_UNIT_NAME")
	private String loadUnitName;

	public String getLoadUnitName() {
		return loadUnitName;
	}

	public void setLoadUnitName(String loadUnitName) {
		this.loadUnitName = loadUnitName;
	}

	public Long getLoadUnitId() {
		return loadUnitId;
	}

	public void setLoadUnitId(Long loadUnitId) {
		this.loadUnitId = loadUnitId;
	}
}