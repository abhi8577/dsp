package com.dsp.dsp.dto;

public class ConsumerApplicationUpdateDto {

	private Long consumerApplicationId;
	private String address;
	private String consumerName;
	private Long consumerId;
	private Long dcId;
	private Long districtId;
	private String guardianName;
	private String khasra;
	private Long landAreaUnitId;
	private Double area;
	private Long loadRequestedId;
	private String loadRequested;
	private Long natureOfWorkTypeId;
	private String pinCode;
	private Long schemeTypeId;
	private String shortDescriptionOfWork;
	private String castCategory;
	private String mobileNo;
	private String memberId;

	public Long getConsumerApplicationId() {
		return consumerApplicationId;
	}

	public String getLoadRequested() {
		return loadRequested;
	}

	public void setLoadRequested(String loadRequested) {
		this.loadRequested = loadRequested;
	}

	public void setConsumerApplicationId(Long consumerApplicationId) {
		this.consumerApplicationId = consumerApplicationId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getArea() {
		return area;
	}

	public Long getConsumerId() {
		return consumerId;
	}

	public void setConsumerId(Long consumerId) {
		this.consumerId = consumerId;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public String getConsumerName() {
		return consumerName;
	}

	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}

	public Long getDcId() {
		return dcId;
	}

	public void setDcId(Long dcId) {
		this.dcId = dcId;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public String getGuardianName() {
		return guardianName;
	}

	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}

	public String getKhasra() {
		return khasra;
	}

	public void setKhasra(String khasra) {
		this.khasra = khasra;
	}

	public Long getLandAreaUnitId() {
		return landAreaUnitId;
	}

	public void setLandAreaUnitId(Long landAreaUnitId) {
		this.landAreaUnitId = landAreaUnitId;
	}

	public Long getLoadRequestedId() {
		return loadRequestedId;
	}

	public void setLoadRequestedId(Long loadRequestedId) {
		this.loadRequestedId = loadRequestedId;
	}

	public Long getNatureOfWorkTypeId() {
		return natureOfWorkTypeId;
	}

	public void setNatureOfWorkTypeId(Long natureOfWorkTypeId) {
		this.natureOfWorkTypeId = natureOfWorkTypeId;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public Long getSchemeTypeId() {
		return schemeTypeId;
	}

	public void setSchemeTypeId(Long schemeTypeId) {
		this.schemeTypeId = schemeTypeId;
	}

	public String getShortDescriptionOfWork() {
		return shortDescriptionOfWork;
	}

	public void setShortDescriptionOfWork(String shortDescriptionOfWork) {
		this.shortDescriptionOfWork = shortDescriptionOfWork;
	}

	public String getCastCategory() {
		return castCategory;
	}

	public void setCastCategory(String castCategory) {
		this.castCategory = castCategory;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

}