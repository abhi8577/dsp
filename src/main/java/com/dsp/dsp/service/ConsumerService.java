package com.dsp.dsp.service;

import com.dsp.dsp.dto.ConsumerRegDto;
import com.dsp.dsp.dto.CredentialsDto;
import com.dsp.dsp.model.Consumer;
import com.dsp.dsp.response.Response;

public interface ConsumerService {

	Response save(ConsumerRegDto consumerRegDto);
	
	Response getLoginDetails(CredentialsDto credentialsDto);


}