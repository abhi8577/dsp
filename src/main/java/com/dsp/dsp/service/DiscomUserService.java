package com.dsp.dsp.service;

import com.dsp.dsp.dto.ConsumerRegDto;
import com.dsp.dsp.dto.DiscomUserRegDto;
import com.dsp.dsp.response.Response;

public interface DiscomUserService {
	
	Response save(DiscomUserRegDto discomUserRegDto);

}
