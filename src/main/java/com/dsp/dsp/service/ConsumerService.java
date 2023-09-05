package com.dsp.dsp.service;

import com.dsp.dsp.dto.ChangePasswordDto;
import com.dsp.dsp.dto.ConsumerRegDto;
import com.dsp.dsp.dto.CredentialsDto;
import com.dsp.dsp.response.Response;

public interface ConsumerService {

	Response save(ConsumerRegDto consumerRegDto);
	
	Response getLoginDetails(CredentialsDto credentialsDto);

	Response changePwd(ChangePasswordDto changePasswordDto);

	Response consumerDetails(String mobileNo);

}