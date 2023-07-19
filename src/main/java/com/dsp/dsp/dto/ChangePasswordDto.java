package com.dsp.dsp.dto;

public class ChangePasswordDto {

	private Long id;
	private String password;
	
	/**
	 * 
	 */
	public ChangePasswordDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
