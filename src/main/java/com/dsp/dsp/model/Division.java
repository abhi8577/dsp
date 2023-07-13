package com.dsp.dsp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DIVISION")
public class Division {

	@Id
	@Column(name="DIV_ID")
	private Long divId;
	
	@Column(name="DIVISION_CODE")
	private Long divisionCode;
	
	@Column(name="DIVISION_NAME")
	private String divisionName;	
	
	@Column(name="CIRCLE_ID")
	private Long circleId;

	public Division() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getDivisionCode() {
		return divisionCode;
	}

	public void setDivisionCode(Long divisionCode) {
		this.divisionCode = divisionCode;
	}

	public String getDivisionName() {
		return divisionName;
	}

	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	public Long getCircleId() {
		return circleId;
	}

	public void setCircleId(Long circleId) {
		this.circleId = circleId;
	}

	public Long getDivId() {
		return divId;
	}

	public void setDivId(Long divId) {
		this.divId = divId;
	}
}
