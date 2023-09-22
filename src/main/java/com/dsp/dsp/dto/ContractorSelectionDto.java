package com.dsp.dsp.dto;

public class ContractorSelectionDto {

	private Long User_Id;
	private String consumerApplicationNo;
	private String consumerName;
	private String consumerTask;

	public Long getUser_Id() {
		return User_Id;
	}

	public void setUser_Id(Long user_Id) {
		User_Id = user_Id;
	}

	public String getConsumerApplicationNo() {
		return consumerApplicationNo;
	}

	public void setConsumerApplicationNo(String consumerApplicationNo) {
		this.consumerApplicationNo = consumerApplicationNo;
	}

	public String getConsumerName() {
		return consumerName;
	}

	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}

	public String getConsumerTask() {
		return consumerTask;
	}

	public void setConsumerTask(String consumerTask) {
		this.consumerTask = consumerTask;
	}

}
