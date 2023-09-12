package com.dsp.dsp.service;

import com.dsp.dsp.dto.ContractorDetailsDto;
import com.dsp.dsp.dto.ContractorSelectionDto;
import com.dsp.dsp.response.Response;

public interface ContractorService {

	Response contractorSelectionSave(ContractorDetailsDto contractorDetailsDto);
	
	

}
