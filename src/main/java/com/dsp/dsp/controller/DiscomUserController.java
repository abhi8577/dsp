package com.dsp.dsp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsp.dsp.dto.DiscomUserRegDto;
import com.dsp.dsp.response.Response;
import com.dsp.dsp.service.DiscomUserService;

@RestController
@RequestMapping("/discom_user")
public class DiscomUserController {

	@Autowired
	DiscomUserService discomUserService;
	
	@PostMapping("/discom_registration")
	public Response registration(@RequestBody DiscomUserRegDto discomUserRegDto) {
		return discomUserService.save(discomUserRegDto);
		
	}
	
}