package com.dsp.dsp.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;

@Entity
@Table(name = "SUBSTATION")
public class Substation {

	@Id
    @Column(name = "SUBSTATION_ID")
    private Long substationId;

    @Column(name = "DC_ID")
    private Long dcId;

    @Column(name = "SUBSTATION_NAME")
    private String substationName;

    @Column(name = "SUBSTATION_CODE")
    private Long substationCode;

    @Column(name = "IS_ACTIVE")
    private Long isActive;

    @Column(name = "DIVISION_ID")
    private String divisionId;

	public Long getSubstationId() {

		return substationId;

	}

	public void setSubstationId(Long substationId) {

		this.substationId = substationId;

	}

	public Long getDcId() {

		return dcId;

	}

	public void setDcId(Long dcId) {

		this.dcId = dcId;

	}

	public String getSubstationName() {

		return substationName;

	}

	public void setSubstationName(String substationName) {

		this.substationName = substationName;

	}

	public Long getSubstationCode() {

		return substationCode;

	}

	public void setSubstationCode(Long substationCode) {

		this.substationCode = substationCode;

	}

	public Long getIsActive() {

		return isActive;

	}

	public void setIsActive(Long isActive) {

		this.isActive = isActive;

	}

	public String getDivisionId() {

		return divisionId;

	}
	public void setDivisionId(String divisionId) {

		this.divisionId = divisionId;

	}
}