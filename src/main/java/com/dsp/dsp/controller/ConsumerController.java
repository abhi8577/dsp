package com.dsp.dsp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsp.dsp.dto.ConsumerRegDto;
import com.dsp.dsp.model.Consumer;
import com.dsp.dsp.response.Response;
import com.dsp.dsp.service.ConsumerService;

@RestController
@RequestMapping(name = "/consumer")
public class ConsumerController {

	@Autowired
	private ConsumerService consumerService;
	
	@PostMapping(name = "/consumer_registration")
	public Response registration(@RequestBody ConsumerRegDto consumerRegDto) {
		return consumerService.save(consumerRegDto);
		
	}
}
