package com.dsp.dsp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GEO_LOCATION")
public class GeoLocation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "GEO_LOCATION_ID")
	private Long geoLocationId;

	@Column(name = "CONSUMER_APPLICATION_NO", length = 20)
	private String consumerApplicationNo;

	@Column(name = "STARTING_LONGITUDE", length = 20)
	private String startingLongitude;

	@Column(name = "STARTING_LATITUDE", length = 20)
	private String startingLatitude;

	@Column(name = "ENDING_LATITUDE", length = 20)
	private String endingLatitude;

	@Column(name = "ENDING_LONGITUDE", length = 20)
	private String endingLongitude;

	@Column(name="IS_ACTIVE")
	private Boolean isActive;

	@Column(name = "START_DOC_PATH")
	private String startDocPath;

	@Column(name = "END_DOC_PATH")
	private String endDocPath;

	@Column(name="CREATED_TIME")
	private String createdTime;
	
	@Column(name="UPDATED_TIME")
	private String updatedTime;

	public GeoLocation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getGeoLocationId() {
		return geoLocationId;
	}

	public void setGeoLocationId(Long geoLocationId) {
		this.geoLocationId = geoLocationId;
	}

	public String getConsumerApplicationNo() {
		return consumerApplicationNo;
	}

	public void setConsumerApplicationNo(String consumerApplicationNo) {
		this.consumerApplicationNo = consumerApplicationNo;
	}

	public String getStartingLongitude() {
		return startingLongitude;
	}

	public void setStartingLongitude(String startingLongitude) {
		this.startingLongitude = startingLongitude;
	}

	public String getStartingLatitude() {
		return startingLatitude;
	}

	public void setStartingLatitude(String startingLatitude) {
		this.startingLatitude = startingLatitude;
	}

	public String getEndingLatitude() {
		return endingLatitude;
	}

	public void setEndingLatitude(String endingLatitude) {
		this.endingLatitude = endingLatitude;
	}

	public String getEndingLongitude() {
		return endingLongitude;
	}

	public void setEndingLongitude(String endingLongitude) {
		this.endingLongitude = endingLongitude;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getStartDocPath() {
		return startDocPath;
	}

	public void setStartDocPath(String startDocPath) {
		this.startDocPath = startDocPath;
	}

	public String getEndDocPath() {
		return endDocPath;
	}

	public void setEndDocPath(String endDocPath) {
		this.endDocPath = endDocPath;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(String updatedTime) {
		this.updatedTime = updatedTime;
	}

}
