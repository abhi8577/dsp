package com.dsp.dsp.serviceImpl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.dsp.dsp.dto.ConsumerApplicationDto;
import com.dsp.dsp.dto.ContractorDetailsDto;
import com.dsp.dsp.dto.ContractorSelectionDto;
import com.dsp.dsp.dto.QcResponseDto;
import com.dsp.dsp.model.ContractorsDetails;
import com.dsp.dsp.service.ContractorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.dsp.dsp.repository.ContractorsDetailsRepository;
import com.dsp.dsp.response.Response;

@Service
public class ContractorServiceImpl implements ContractorService{
	
	@Autowired
	ContractorsDetailsRepository contractorsDetailsRepository;

	@Override
	@org.springframework.transaction.annotation.Transactional
	public Response contractorSelectionSave(ContractorDetailsDto contractorDetailsDto) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://rooftop-uat.mpcz.in:8443/qcp/contractor_selection_save";
      
        ContractorsDetails saveContractorsDetails=new ContractorsDetails();
		ContractorsDetails contractorDetailsWithAppNo=new ContractorsDetails();
		int dspDataCheck=0;
		int qcDataCheck=0;
		if(contractorDetailsDto.getUserId()!=null && contractorDetailsDto.getConsumerApplicationNo()!=null) {
		try {

			contractorDetailsWithAppNo.setConsumerApplicationNo(contractorDetailsDto.getConsumerApplicationNo());
			contractorDetailsWithAppNo.setAuthenticationId(contractorDetailsDto.getAuthenticationId());
			contractorDetailsWithAppNo.setAuthorisedPerson(contractorDetailsDto.getAuthorisedPerson());
			contractorDetailsWithAppNo.setCompanyAddress1(contractorDetailsDto.getCompanyAddress1());
			contractorDetailsWithAppNo.setCompanyAddress2(contractorDetailsDto.getCompanyAddress2());
			contractorDetailsWithAppNo.setCompanyId(contractorDetailsDto.getCompanyId());
			contractorDetailsWithAppNo.setCompanyName(contractorDetailsDto.getCompanyName());
			contractorDetailsWithAppNo.setCompanyPinCode(contractorDetailsDto.getCompanyPinCode());
			contractorDetailsWithAppNo.setCompanyTCity(contractorDetailsDto.getCompanyTCity());
			contractorDetailsWithAppNo.setCompanyTDistrict(contractorDetailsDto.getCompanyTDistrict());
			contractorDetailsWithAppNo.setCompanyTState(contractorDetailsDto.getCompanyTState());
			contractorDetailsWithAppNo.setCreationDate(LocalDateTime.now().toString());
			contractorDetailsWithAppNo.setOyt(contractorDetailsDto.getOyt());
			contractorDetailsWithAppNo.setOytName(contractorDetailsDto.getOytName());
			contractorDetailsWithAppNo.setUserId(contractorDetailsDto.getUserId());
			contractorDetailsWithAppNo.setUserType(contractorDetailsDto.getUserType());
			
			saveContractorsDetails = contractorsDetailsRepository.save(contractorDetailsWithAppNo);
			dspDataCheck=1;
			if(saveContractorsDetails==null) {
				 return Response.response("Data not save in dsp",HttpStatus.NOT_FOUND,null,null);

			}
		
			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-Type", "application/json");

            HttpEntity<ContractorsDetails> httpEntity = new HttpEntity<>(saveContractorsDetails,headers);	

			ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, httpEntity, String.class);
			System.out.println("responseEntity------------->" + responseEntity);
			
			String body = responseEntity.getBody();

			ObjectMapper objectMapper = new ObjectMapper();

			QcResponseDto qcResponseDto = objectMapper.readValue(body, QcResponseDto.class);
			
			if(qcResponseDto.getStatus()==200){
				qcDataCheck=1;
				 return Response.response("Data save in dsp and qc",HttpStatus.NOT_FOUND,null,null);
			}
			else {
			      if(dspDataCheck==1) {
			    	  contractorsDetailsRepository.deleteById(saveContractorsDetails.getSrNo()); 
						 return Response.response("Data not save in qc portal so delete from dsp",HttpStatus.OK,null,null);

			      }
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(dspDataCheck);
			 if(dspDataCheck==1 && qcDataCheck==0) {
		    	  contractorsDetailsRepository.deleteById(saveContractorsDetails.getSrNo()); 
			 }
			 return Response.response("",HttpStatus.INTERNAL_SERVER_ERROR,null,e.getMessage());

		}
	}
	 return Response.response("user id and application no. should not be null",HttpStatus.BAD_REQUEST,null,null);


	}

	
	

}
