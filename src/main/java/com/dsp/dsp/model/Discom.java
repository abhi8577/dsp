package com.dsp.dsp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DISCOM")
public class Discom {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DISCOM_ID")
	private Long discomId;

	@Column(name = "DISCOM_NAME")
	private String discomName;

	public Long getDiscomId() {
		return discomId;
	}

	public void setDiscomId(Long discomId) {
		this.discomId = discomId;
	}

	public String getDiscomName() {
		return discomName;
	}

	public void setDiscomName(String discomName) {
		this.discomName = discomName;
	}

	
}
