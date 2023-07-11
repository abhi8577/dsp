package com.dsp.dsp.dto;

import javax.persistence.Column;

public class ConsumerRegDto {
	
	
	private String consumerName;
	
	private String mobileNumber;
	
	private String email;
	
	private  String address;
	 
	private String password;
	

	public ConsumerRegDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getConsumerName() {
		return consumerName;
	}

	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "ConsumerRegDto [consumerName=" + consumerName + ", mobileNumber=" + mobileNumber + ", email=" + email
				+ ", address=" + address + ", password=" + password + "]";
	}
	
	
	

}
