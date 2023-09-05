package com.dsp.dsp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="APPLY_TYPE")
public class ApplyType {
	
	@Id
	@Column(name="APPLY_TYPE_ID")
	private Long applyTypeId;
	
	@Column(name="APPLY_TYPE_NAME")
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

	
	
	

}
