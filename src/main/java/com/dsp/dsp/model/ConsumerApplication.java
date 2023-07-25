package com.dsp.dsp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CONSUMER_APPLICATION")
public class ConsumerApplication {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="SR_NO")
	private Long sr_No;
	
	@Column(name="CONSUMER_APPLICATION_ID")
	private String consumerApplicationId;
	
	@Column(name="NATURE_OF_WORK_ID")
	private Long natureOfWorkId;
	
	@Column(name = "DTR")
    private Long dtr;

    @Column(name = "HT_11_KV")
    private Long ht11KV;

    @Column(name = "HT_132_KV")
    private Long ht132KV;

    @Column(name = "HT_33_KV")
    private Long ht33KV;

    @Column(name = "LT")
    private Long lt;
    
    @Column(name = "PTR")
    private Long ptr;
    
    @Column(name = "SCHEME_TYPE_ID")
    private Long schemeTypeId;
    
    @Column(name = "CONSUMER_ID")
    private Long consumerId;
   
    @Column(name = "GUARDIAN_NAME")
    private String guardianName;
    
    @Column(name = "ADDRESS")
    private String address;
    
    @Column(name = "WORK_LOCATION_ADDR")
    private String workLocationAddr;
    
    @Column(name = "PINCODE")
    private Long pincode;
    
    @Column(name = "DISTRICT_ID")
    private Long districtId;
    
    @Column(name = "DC_ID")
    private Long dcId;
    
    @Column(name = "DESCRIPTION_OF_WORK")
    private String descriptionOfWork;
    
    @Column(name = "ADMINISTRATIVE_FILE_PATH")
    private String administrativeFilePath;
    
    @Column(name = "GST_NUMBER")
    private String gstNo;
    
    @Column(name = "GST_FILE_PATH")
    private String gstFilePath;
    
    @Column(name = "IVRS_NO")
    private String ivrsNo;
    
    @Column(name = "LOAD_REQUESTED")
    private Long loadRequested;
    
    @Column(name = "LOAD_UNIT_ID")
    private Long loadUnitId;
    
    @Column(name = "LAND_AREA")
    private String landArea;
    
    @Column(name = "LAND_AREA_UNIT_ID")
    private Long landAreaUnitId;
    
    @Column(name = "NO_OF_PLOTS")
    private String noOfPlots;
    
    @Column(name = "APPLY_TYPE_ID")
    private Long applyTypeId;
    
    
    @Column(name = "T_AND_CP_PERMISSION_FILE_PATH")
    private String TAndCPpermissionFilePath;
    
    
    @Column(name = "RERA_PERMISSION_FILE_PATH")
    private String reraPermissionFilePath;
    
    
    @Column(name = "GROUP_PERMISSION_FILE_PATH")
    private String grouppermissionFilePath;
    
    @Column(name = "REGISTRY_FILE_PATH")
    private String registryFilePath;
    
    
    @Column(name = "NOC_FILE_PATH")
    private String NOCfilePath;
    
    @Column(name = "KHASRA")
    private String khasra;
    
    @Column(name = "KHATONI")
    private String khatoni;
    
    @Column(name = "KHASRA_KHATONI_FILE_PATH")
    private String khasraKhatoniFilePath;
    
    @Column(name="CREATED_TIME")
	private String createdTime;
    
    @Column(name="IS_ACTIVE")
   	private Boolean isActive;
    
    @Column(name="APPLICATION_STATUS_ID")
   	private Long applicationStatusId;
    
    @Column(name="ERP_PROJECT_NUMBER")
   	private String erpProjectNumber;
    
	public ConsumerApplication() {
		super();
	}


	public String getConsumerApplicationId() {
		return consumerApplicationId;
	}


	public void setConsumerApplicationId(String consumerApplicationId) {
		this.consumerApplicationId = consumerApplicationId;
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


	public String getAdministrativeFilePath() {
		return administrativeFilePath;
	}


	public void setAdministrativeFilePath(String administrativeFilePath) {
		this.administrativeFilePath = administrativeFilePath;
	}


	public String getGstNo() {
		return gstNo;
	}


	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}


	public String getGstFilePath() {
		return gstFilePath;
	}


	public void setGstFilePath(String gstFilePath) {
		this.gstFilePath = gstFilePath;
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


	public String getTAndCPpermissionFilePath() {
		return TAndCPpermissionFilePath;
	}


	public void setTAndCPpermissionFilePath(String tAndCPpermissionFilePath) {
		TAndCPpermissionFilePath = tAndCPpermissionFilePath;
	}


	public String getReraPermissionFilePath() {
		return reraPermissionFilePath;
	}


	public void setReraPermissionFilePath(String reraPermissionFilePath) {
		this.reraPermissionFilePath = reraPermissionFilePath;
	}


	public String getRegistryFilePath() {
		return registryFilePath;
	}


	public void setRegistryFilePath(String registryFilePath) {
		this.registryFilePath = registryFilePath;
	}


	public String getNOCfilePath() {
		return NOCfilePath;
	}


	public void setNOCfilePath(String nOCfilePath) {
		NOCfilePath = nOCfilePath;
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


	public String getKhasraKhatoniFilePath() {
		return khasraKhatoniFilePath;
	}


	public void setKhasraKhatoniFilePath(String khasraKhatoniFilePath) {
		this.khasraKhatoniFilePath = khasraKhatoniFilePath;
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


	public String getGrouppermissionFilePath() {
		return grouppermissionFilePath;
	}


	public void setGrouppermissionFilePath(String grouppermissionFilePath) {
		this.grouppermissionFilePath = grouppermissionFilePath;
	}


	public Long getPtr() {
		return ptr;
	}


	public void setPtr(Long ptr) {
		this.ptr = ptr;
	}


	public Long getSr_No() {
		return sr_No;
	}


	public void setSr_No(Long sr_No) {
		this.sr_No = sr_No;
	}
	public Long getApplicationStatusId() {
		return applicationStatusId;
	}

	public void setApplicationStatusId(Long applicationStatusId) {
		this.applicationStatusId = applicationStatusId;
	}


	public String getErpProjectNumber() {
		return erpProjectNumber;
	}


	public void setErpProjectNumber(String erpProjectNumber) {
		this.erpProjectNumber = erpProjectNumber;
	}
}