package com.dsp.dsp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="LOAD_REQUESTED")
public class LoadRequested {
	
	@Id
	@Column(name="LOAD_REQUESTED_ID")
	private Long loadRequestedId;
	
	@Column(name="LOAD_UNIT_NAME")
	private String loadUnitName;

	public Long getLoadRequestedId() {
		return loadRequestedId;
	}

	public void setLoadRequestedId(Long loadRequestedId) {
		this.loadRequestedId = loadRequestedId;
	}

	public String getLoadUnitName() {
		return loadUnitName;
	}

	public void setLoadUnitName(String loadUnitName) {
		this.loadUnitName = loadUnitName;
	}
	
	
	


}
