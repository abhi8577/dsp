package com.dsp.dsp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACCESS_LEVEL")
public class AccessLevel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ACCESS_LEVEL_ID")
	private Long accessLevelId;

	@Column(name = "ACCESS_LEVEL_NAME")
	private String accessLevelName;

	public AccessLevel() {
		super();
	}

	public Long getAccessLevelId() {
		return accessLevelId;
	}

	public void setAccessLevelId(Long accessLevelId) {
		this.accessLevelId = accessLevelId;
	}

	public String getAccessLevelName() {
		return accessLevelName;
	}

	public void setAccessLevelName(String accessLevelName) {
		this.accessLevelName = accessLevelName;
	}
}