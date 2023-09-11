package com.dsp.dsp.model;

import javax.persistence.Entity;

import javax.persistence.Id;

import javax.persistence.Column;

import javax.persistence.Table;



@Entity
@Table(name = "FEEDER")
public class Feeder {



    @Id
    @Column(name = "FEEDER_ID")
    private Long feederId;

    @Column(name = "SUBSTATION_ID")

    private Long subStationId;



    @Column(name = "FEEDER_NAME", length = 260)

    private String feederName;



    @Column(name = "FEEDER_CODE")

    private Long feederCode;



    @Column(name = "IS_ACTIVE")

    private Long isActive;



	public Long getFeederId() {

		return feederId;

	}



	public void setFeederId(Long feederId) {

		this.feederId = feederId;

	}





	public Long getSubStationId() {

		return subStationId;

	}



	public void setSubStationId(Long subStationId) {

		this.subStationId = subStationId;

	}



	public String getFeederName() {

		return feederName;

	}



	public void setFeederName(String feederName) {

		this.feederName = feederName;

	}



	public Long getFeederCode() {

		return feederCode;

	}



	public void setFeederCode(Long feederCode) {

		this.feederCode = feederCode;

	}



	public Long getIsActive() {

		return isActive;

	}


	public void setIsActive(Long isActive) {

		this.isActive = isActive;

	}


}
