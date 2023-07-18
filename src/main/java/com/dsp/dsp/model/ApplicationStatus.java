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
	private Long applicationStatusName;

	public Long getApplicationStatusId() {
		return applicationStatusId;
	}

	public void setApplicationStatusId(Long applicationStatusId) {
		this.applicationStatusId = applicationStatusId;
	}

	public Long getApplicationStatusName() {
		return applicationStatusName;
	}

	public void setApplicationStatusName(Long applicationStatusName) {
		this.applicationStatusName = applicationStatusName;
	}
	
	
	

}
