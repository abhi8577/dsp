package com.dsp.dsp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SCHEME_TYPE")
public class SchemeType {
	
	@Id
	@Column(name="SCHEME_TYPE_ID")
	private Long schemeTypeId;
	
	@Column(name="SCHEME_TYPE_NAME")
	private String schemeTypeName;

	public Long getSchemeTypeId() {
		return schemeTypeId;
	}

	public void setSchemeTypeId(Long schemeTypeId) {
		this.schemeTypeId = schemeTypeId;
	}

	public String getSchemeTypeName() {
		return schemeTypeName;
	}

	public void setSchemeTypeName(String schemeTypeName) {
		this.schemeTypeName = schemeTypeName;
	}
	
	
	

}
