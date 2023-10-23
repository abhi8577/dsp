package com.dsp.dsp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DESIGNATION")
public class Designation {

	@Id
	@Column(name = "DESIGNATION_ID")
	private Long designationId;

	@Column(name = "DESIGNATION")
	private String designationName;

	public Designation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getDesignationId() {
		return designationId;
	}

	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
	}

	public String getDesignationName() {
		return designationName;
	}

	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}
}