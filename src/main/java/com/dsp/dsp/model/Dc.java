package com.dsp.dsp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DC")
public class Dc {
	
	@Id
	@Column(name="DC_CODE")
	private Long dcCode;
	
	@Column(name="DC_NAME")
	private String dcName;

	

	public Long getDcCode() {
		return dcCode;
	}

	public void setDcCode(Long dcCode) {
		this.dcCode = dcCode;
	}

	public String getDcName() {
		return dcName;
	}

	public void setDcName(String dcName) {
		this.dcName = dcName;
	}
	
	

}
