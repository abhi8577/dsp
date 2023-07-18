package com.dsp.dsp.dto;

public class PendingForGeoLocationApplicationDto {
	
	private String consumerApplicationId;
	
	private String applicationStatus;

	public PendingForGeoLocationApplicationDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getConsumerApplicationId() {
		return consumerApplicationId;
	}

	public void setConsumerApplicationId(String consumerApplicationId) {
		this.consumerApplicationId = consumerApplicationId;
	}

	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
}