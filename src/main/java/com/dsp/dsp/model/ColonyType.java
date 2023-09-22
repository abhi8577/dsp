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
	private Long applyTypeId;

	@Column(name = "COLONY_TYPE_NAME")
	private String applyTypeName;

	public ColonyType() {
		super();
		// TODO Auto-generated constructor stub
	}

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

}
