package com.dsp.dsp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsp.dsp.dto.ContractorDetailsDto;
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
