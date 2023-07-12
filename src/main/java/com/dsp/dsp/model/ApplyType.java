package com.dsp.dsp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="APPLY_TYPE")
public class ApplyType {
	
	@Id
	@Column(name="DOCUMENT_TYPE_ID")
	private Long documentTypeId;
	
	@Column(name="DOCUMENT_TYPE_NAME")
	private String documentTypeName;

	public Long getDocumentTypeId() {
		return documentTypeId;
	}

	public void setDocumentTypeId(Long documentTypeId) {
		this.documentTypeId = documentTypeId;
	}

	public String getDocumentTypeName() {
		return documentTypeName;
	}

	public void setDocumentTypeName(String documentTypeName) {
		this.documentTypeName = documentTypeName;
	}
	
	
	

}
