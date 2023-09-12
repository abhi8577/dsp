package com.dsp.dsp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.dsp.dsp.dto.ContractorDetailsDto;
import com.dsp.dsp.dto.ContractorSelectionDto;
import com.dsp.dsp.service.ContractorService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/contractor")
public class ContractorController {
	
	@Autowired
	ContractorService contractorService;
	
	@PostMapping("/contractor_selection_save")
	void contractorSelectionSave(@RequestBody ContractorDetailsDto contractorDetailsDto) {
		
		contractorService.contractorSelectionSave(contractorDetailsDto);
		
	}

}
