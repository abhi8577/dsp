package com.dsp.dsp.dto;

import javax.persistence.Column;

import org.springframework.web.multipart.MultipartFile;

public class ConsumerApplicationDto {
	
	
	
	private Long natureOfWorkId;
	
    private Long dtr;

    private Long ht11KV;

    private Long ht132KV;

    private Long ht33KV;

    private Long lt;
    
    private Long schemeTypeId;
    
    private Long consumerId;
   
    private String guardianName;
    
    private String address;
    
    private String workLocationAddr;
    
    private Long pincode;
    
    private Long districtId;
    
    private Long dcId;
    
    private String descriptionOfWork;
      
    private String gstNo;
        
    private String ivrsNo;
    
    private Long loadRequested;
    
    private Long loadUnitId;
    
    private String landArea;
    
    private Long landAreaUnitId;
    
    private String noOfPlots;
    
    private Long applyTypeId;
    
    private String khasra;
    
    private String khatoni;
     
//    private MultipartFile TAndCPpermissionFile;
//     
//    private MultipartFile reraPermissionFile;
//    
//    private MultipartFile grouppermissionFile;
//      
//    private MultipartFile registryFile;
//     
//    private MultipartFile NOCfile;
//    
//    private MultipartFile administrativeFile;
//
//    private MultipartFile gstFile;
//    
//    private MultipartFile khasraKhatoniFile;

	public ConsumerApplicationDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getNatureOfWorkId() {
		return natureOfWorkId;
	}

	public void setNatureOfWorkId(Long natureOfWorkId) {
		this.natureOfWorkId = natureOfWorkId;
	}

	public Long getDtr() {
		return dtr;
	}

	public void setDtr(Long dtr) {
		this.dtr = dtr;
	}

	public Long getHt11KV() {
		return ht11KV;
	}

	public void setHt11KV(Long ht11kv) {
		ht11KV = ht11kv;
	}

	public Long getHt132KV() {
		return ht132KV;
	}

	public void setHt132KV(Long ht132kv) {
		ht132KV = ht132kv;
	}

	public Long getHt33KV() {
		return ht33KV;
	}

	public void setHt33KV(Long ht33kv) {
		ht33KV = ht33kv;
	}

	public Long getLt() {
		return lt;
	}

	public void setLt(Long lt) {
		this.lt = lt;
	}

	public Long getSchemeTypeId() {
		return schemeTypeId;
	}

	public void setSchemeTypeId(Long schemeTypeId) {
		this.schemeTypeId = schemeTypeId;
	}

	public Long getConsumerId() {
		return consumerId;
	}

	public void setConsumerId(Long consumerId) {
		this.consumerId = consumerId;
	}

	public String getGuardianName() {
		return guardianName;
	}

	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWorkLocationAddr() {
		return workLocationAddr;
	}

	public void setWorkLocationAddr(String workLocationAddr) {
		this.workLocationAddr = workLocationAddr;
	}

	public Long getPincode() {
		return pincode;
	}

	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public Long getDcId() {
		return dcId;
	}

	public void setDcId(Long dcId) {
		this.dcId = dcId;
	}

	public String getDescriptionOfWork() {
		return descriptionOfWork;
	}

	public void setDescriptionOfWork(String descriptionOfWork) {
		this.descriptionOfWork = descriptionOfWork;
	}

	public String getGstNo() {
		return gstNo;
	}

	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}

	public String getIvrsNo() {
		return ivrsNo;
	}

	public void setIvrsNo(String ivrsNo) {
		this.ivrsNo = ivrsNo;
	}

	public Long getLoadRequested() {
		return loadRequested;
	}

	public void setLoadRequested(Long loadRequested) {
		this.loadRequested = loadRequested;
	}

	public Long getLoadUnitId() {
		return loadUnitId;
	}

	public void setLoadUnitId(Long loadUnitId) {
		this.loadUnitId = loadUnitId;
	}

	public String getLandArea() {
		return landArea;
	}

	public void setLandArea(String landArea) {
		this.landArea = landArea;
	}

	public Long getLandAreaUnitId() {
		return landAreaUnitId;
	}

	public void setLandAreaUnitId(Long landAreaUnitId) {
		this.landAreaUnitId = landAreaUnitId;
	}

	public String getNoOfPlots() {
		return noOfPlots;
	}

	public void setNoOfPlots(String noOfPlots) {
		this.noOfPlots = noOfPlots;
	}

	public Long getApplyTypeId() {
		return applyTypeId;
	}

	public void setApplyTypeId(Long applyTypeId) {
		this.applyTypeId = applyTypeId;
	}

	public String getKhasra() {
		return khasra;
	}

	public void setKhasra(String khasra) {
		this.khasra = khasra;
	}

	public String getKhatoni() {
		return khatoni;
	}

	public void setKhatoni(String khatoni) {
		this.khatoni = khatoni;
	}

	
	
    

}