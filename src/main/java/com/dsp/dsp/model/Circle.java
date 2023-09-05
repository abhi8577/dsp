package com.dsp.dsp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CIRCLE")
public class Circle {

	@Id
	@Column(name="CIRCLE_ID")
	private Long circleId;
	
	@Column(name="CIRCLE_CODE")
	private Long circleCode;
	
	@Column(name="CIRCLE_NAME")
	private String circleName;	
	
	@Column(name="REGION_CODE")
	private Long regionCode;

	public Circle() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getCircleCode() {
		return circleCode;
	}

	public void setCircleCode(Long circleCode) {
		this.circleCode = circleCode;
	}

	public String getCircleName() {
		return circleName;
	}

	public void setCircleName(String circleName) {
		this.circleName = circleName;
	}

	public Long getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(Long regionCode) {
		this.regionCode = regionCode;
	}

	public Long getCircleId() {
		return circleId;
	}

	public void setCircleId(Long circleId) {
		this.circleId = circleId;
	}

}