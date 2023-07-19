package com.dsp.dsp.service;


import com.dsp.dsp.dto.ChangePasswordDto;
import com.dsp.dsp.dto.CredentialsDto;

import com.dsp.dsp.dto.DiscomUserRegDto;
import com.dsp.dsp.response.Response;

public interface DiscomUserService {
	
	Response save(DiscomUserRegDto discomUserRegDto);
	
	Response getLoginDetails(CredentialsDto credentialsDto);

	Response changePwd(ChangePasswordDto changePasswordDto);

	Response dsicomUserDetails(String mobileNo);

}