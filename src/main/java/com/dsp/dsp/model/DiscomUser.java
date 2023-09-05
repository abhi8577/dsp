package com.dsp.dsp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "DISCOM_USER")
public class DiscomUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SR_NO")
	private Long srNo;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "USERID")
	private String userId;

	@JsonIgnore
	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "MOBILE_NO")
	private String mobileNo;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "DISCOM_ID")
	private Long discomId;

	@Column(name = "REGION_ID")
	private Long regionId;

	@Column(name = "CIRCLE_ID")
	private Long circleId;

	@Column(name = "DIV_ID")
	private Long divId;

	@Column(name = "SUB_DIVISION_ID")
	private Long subDivisionId;

	@Column(name = "DC_ID")
	private Long dcId;

	@Column(name = "SUBSTATION_ID")
	private Long substationId;

	@Column(name = "FEEDER_ID")
	private Long feederId;

	@Column(name = "DESIGNATION_ID")
	private Long designationId;
	
	@Column(name = "ACCESS_LEVEL")
    private String accessLevel;

	@Column(name="CREATED_TIME")
	private String createdTime;

	@Column(name = "IS_ACTIVE")
	private Boolean isActive;
	
	@Column(name="ROLE_ID")
	private Long roleId;
	

	public DiscomUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Long getSrNo() {
		return srNo;
	}



	public void setSrNo(Long srNo) {
		this.srNo = srNo;
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

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	
	
}
