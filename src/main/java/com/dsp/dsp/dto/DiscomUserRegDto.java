package com.dsp.dsp.dto;

import javax.persistence.Column;

public class DiscomUserRegDto {
	
	
	private String userName;

	
	private String userId;

	
	private String password;

	
	private String mobileNo;

	
	private String email;

	
	private Long discomId;

	
	private Long regionId;

	
	private Long circleId;

	
	private Long divId;

	
	private Long subDivisionId;

	
	private Long dcId;

	
	private Long substationId;

	
	private Long feederId;

	
	private Long designationId;
	
	
    private String accessLevel;


	public DiscomUserRegDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getMobileNo() {
		return mobileNo;
	}


	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Long getDiscomId() {
		return discomId;
	}


	public void setDiscomId(Long discomId) {
		this.discomId = discomId;
	}


	public Long getRegionId() {
		return regionId;
	}


	public void setRegionId(Long regionId) {
		this.regionId = regionId;
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


	public Long getSubDivisionId() {
		return subDivisionId;
	}


	public void setSubDivisionId(Long subDivisionId) {
		this.subDivisionId = subDivisionId;
	}


	public Long getDcId() {
		return dcId;
	}


	public void setDcId(Long dcId) {
		this.dcId = dcId;
	}


	public Long getSubstationId() {
		return substationId;
	}


	public void setSubstationId(Long substationId) {
		this.substationId = substationId;
	}


	public Long getFeederId() {
		return feederId;
	}


	public void setFeederId(Long feederId) {
		this.feederId = feederId;
	}


	public Long getDesignationId() {
		return designationId;
	}


	public void setDesignationId(Long designationId) {
		this.designationId = designationId;
	}


	public String getAccessLevel() {
		return accessLevel;
	}


	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
	}
    
    
    


}
