package com.dsp.dsp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ERP_ESTIMATE")
public class ERPEstimate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "SUPERVISION_COST")
    private Double supervisionCost;

    @Column(name = "LONG_NAME")
    private String longName;

    @Column(name = "ESTIMATED_COST")
    private Double estimatedCost;

    @Column(name = "ORG")
    private String org;

    @Column(name = "ORG1")
    private String org1;

    @Column(name = "ESTIMATE_NO")
    private String estimateNo;

    @Column(name = "SUBMITTED_BY_NAME")
    private String submittedByName;

    @Column(name = "JOB1")
    private String job1;

    @Column(name = "CREATED_USER_NAME")
    private String createdUserName;

    @Column(name = "APPROVED_BY_NAME")
    private String approvedByName;

    @Column(name = "PROJECT_NUMBER")
    private String projectNumber;

    @Column(name = "PROJECT_TYPE")
    private String projectType;

    @Column(name = "PROJECT_ID")
    private Long projectId;

    @Column(name = "SCHEMECODE")
    private String schemeCode;

    @Column(name = "SANCT_COST")
    private Double sanctCost;

    @Column(name = "DESIG")
    private String desig;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "CREATED_USER_ID")
    private String createdUserId;

    @Column(name = "ESTIMATE_DATE")
    private String estimateDate;

    @Column(name = "ORGANIZATION_NAME")
    private String organizationName;
    
    @Column(name = "ERP_ESTIMATE_FILE_PATH")
    private String erpEstimateFilePath;
    
    @Column(name="CREATED_TIME")
	private String createdTime;

	public ERPEstimate() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getSupervisionCost() {
		return supervisionCost;
	}

	public void setSupervisionCost(Double supervisionCost) {
		this.supervisionCost = supervisionCost;
	}

	public String getLongName() {
		return longName;
	}

	public void setLongName(String longName) {
		this.longName = longName;
	}

	public Double getEstimatedCost() {
		return estimatedCost;
	}

	public void setEstimatedCost(Double estimatedCost) {
		this.estimatedCost = estimatedCost;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getOrg1() {
		return org1;
	}

	public void setOrg1(String org1) {
		this.org1 = org1;
	}

	public String getEstimateNo() {
		return estimateNo;
	}

	public void setEstimateNo(String estimateNo) {
		this.estimateNo = estimateNo;
	}

	public String getSubmittedByName() {
		return submittedByName;
	}

	public void setSubmittedByName(String submittedByName) {
		this.submittedByName = submittedByName;
	}

	public String getJob1() {
		return job1;
	}

	public void setJob1(String job1) {
		this.job1 = job1;
	}

	public String getCreatedUserName() {
		return createdUserName;
	}

	public void setCreatedUserName(String createdUserName) {
		this.createdUserName = createdUserName;
	}

	public String getApprovedByName() {
		return approvedByName;
	}

	public void setApprovedByName(String approvedByName) {
		this.approvedByName = approvedByName;
	}

	public String getProjectNumber() {
		return projectNumber;
	}

	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getSchemeCode() {
		return schemeCode;
	}

	public void setSchemeCode(String schemeCode) {
		this.schemeCode = schemeCode;
	}

	public Double getSanctCost() {
		return sanctCost;
	}

	public void setSanctCost(Double sanctCost) {
		this.sanctCost = sanctCost;
	}

	public String getDesig() {
		return desig;
	}

	public void setDesig(String desig) {
		this.desig = desig;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(String createdUserId) {
		this.createdUserId = createdUserId;
	}

	public String getEstimateDate() {
		return estimateDate;
	}

	public void setEstimateDate(String estimateDate) {
		this.estimateDate = estimateDate;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getErpEstimateFilePath() {
		return erpEstimateFilePath;
	}

	public void setErpEstimateFilePath(String erpEstimateFilePath) {
		this.erpEstimateFilePath = erpEstimateFilePath;
	}
}