package com.dsp.dsp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SUB_DIVISION")
public class SubDivision {

	@Id
	@Column(name="SUBDIV_ID")
	private Long subDivId;
	
	@Column(name="SUB_DIV_CODE")
	private Long subDivisionCode;
	
	@Column(name="SUB_DIVISION")
	private String subDivisionName;	
	
	@Column(name="DIVISION_ID")
	private Long divisionId;

	public SubDivision() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getSubDivId() {
		return subDivId;
	}

	public void setSubDivId(Long subDivId) {
		this.subDivId = subDivId;
	}

	public Long getSubDivisionCode() {
		return subDivisionCode;
	}

	public void setSubDivisionCode(Long subDivisionCode) {
		this.subDivisionCode = subDivisionCode;
	}

	public String getSubDivisionName() {
		return subDivisionName;
	}

	public void setSubDivisionName(String subDivisionName) {
		this.subDivisionName = subDivisionName;
	}

	public Long getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(Long divisionId) {
		this.divisionId = divisionId;
	}

}