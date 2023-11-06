package com.dsp.dsp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COLONY_TYPE")
public class ColonyType {

	@Id
	@Column(name = "COLONY_TYPE_ID")
	private Long colonyTypeId;

	@Column(name = "COLONY_TYPE_NAME")
	private String colonyTypeName;

	public ColonyType() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Long getColonyTypeId() {
		return colonyTypeId;
	}


	public void setColonyTypeId(Long colonyTypeId) {
		this.colonyTypeId = colonyTypeId;
	}


	public String getColonyTypeName() {
		return colonyTypeName;
	}


	public void setColonyTypeName(String colonyTypeName) {
		this.colonyTypeName = colonyTypeName;
	}


	
}
