package com.dsp.dsp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="APPLICATION_STATUS")
public class ApplicationStatus {
	
	@Id
	@Column(name="APPLICATION_STATUS_ID")
	private Long applicationStatusId;
	
	@Column(name="APPLICATION_STATUS_NAME")
	private String applicationStatusName;

	public Long getApplicationStatusId() {
		return applicationStatusId;
	}

	public void setApplicationStatusId(Long applicationStatusId) {
		this.applicationStatusId = applicationStatusId;
	}

	public String getApplicationStatusName() {
		return applicationStatusName;
	}

	public void setApplicationStatusName(String applicationStatusName) {
		this.applicationStatusName = applicationStatusName;
	}

}
