package com.dsp.dsp.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.dsp.dsp.dto.ConsumerApplicationDto;
import com.dsp.dsp.dto.ContractorDetailsDto;
import com.dsp.dsp.dto.ContractorSelectionDto;
import com.dsp.dsp.model.ContractorsDetails;
import com.dsp.dsp.service.ContractorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.dsp.dsp.repository.ContractorsDetailsRepository;

@Service
public class ContractorServiceImpl implements ContractorService {

	@Autowired
	ContractorsDetailsRepository contractorsDetailsRepository;

	@Override
	public void contractorSelectionSave(ContractorDetailsDto contractorDetailsDto) {
		RestTemplate restTemplate = new RestTemplate();

		String url = "http://localhost:8080/dsp/contractor/contractor_selection_save";

		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-Type", "application/json");

			HttpEntity<ContractorsDetails> httpEntity = new HttpEntity<>(headers);

			ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, httpEntity, String.class);
			System.out.println("responseEntity------------->" + responseEntity);

			String body = responseEntity.getBody();
			if (body == null) {

			}
			ObjectMapper objectMapper = new ObjectMapper();

			ContractorsDetails contractorsDetails = objectMapper.readValue(body, ContractorsDetails.class);

			contractorsDetailsRepository.save(contractorsDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
