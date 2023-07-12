package com.dsp.dsp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="NATURE_OF_WORK")
public class NatureOfWork {
	
	@Id
	@Column(name="NATURE_OF_WORK_ID")
	private Long natureOfWorkId;
	
	@Column(name="NATURE_OF_WORK_NAME")
	private String natureOfWorkName;

	public Long getNatureOfWorkId() {
		return natureOfWorkId;
	}

	public void setNatureOfWorkId(Long natureOfWorkId) {
		this.natureOfWorkId = natureOfWorkId;
	}

	public String getNatureOfWorkName() {
		return natureOfWorkName;
	}

	public void setNatureOfWorkName(String natureOfWorkName) {
		this.natureOfWorkName = natureOfWorkName;
	}
	
	
	

}
