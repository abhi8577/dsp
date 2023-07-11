package com.dsp.dsp.serviceImpl;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Base64.Encoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dsp.dsp.dto.ConsumerRegDto;
import com.dsp.dsp.model.Consumer;
import com.dsp.dsp.repository.ConsumerRepository;
import com.dsp.dsp.response.Response;
import com.dsp.dsp.service.ConsumerService;

@Service
public class ConsumerServiceImpl implements ConsumerService {

	@Autowired
	private ConsumerRepository consumerRepository;
	
	@Override
	public Response save(ConsumerRegDto consumerRegDto) {
		
		try {
			
			if (consumerRegDto == null) {

	return Response.response("Consumer request should not be null", HttpStatus.BAD_REQUEST, null, null);
			}
			String consumerName = consumerRegDto.getConsumerName();
			String mobileNumber = consumerRegDto.getMobileNumber();
			String email = consumerRegDto.getEmail();
			String password = consumerRegDto.getPassword();
			String address = consumerRegDto.getAddress();
			
			
			if(mobileNumber==null || mobileNumber.isEmpty()) {
				return Response.response("Mobile number should not be null", HttpStatus.BAD_REQUEST, null, null);

			}
			
			if(password==null || password.isEmpty()) {	
				return Response.response("Password should not be null", HttpStatus.BAD_REQUEST, null, null);
	
			}
			
			if(consumerName==null || consumerName.isEmpty()) {

		return Response.response("Consumer name should not be null", HttpStatus.BAD_REQUEST, null, null);

			}	
			
			
			if(email==null || email.isEmpty()) {

			return Response.response("Email id should not be null", HttpStatus.BAD_REQUEST, null, null);

			}
			if(address==null || address.isEmpty()) {
				return Response.response("Address should not be null", HttpStatus.BAD_REQUEST, null, null);
		
			}
			
			String trimConsumerName = consumerName.replaceAll("\\s+", " ").trim().toUpperCase();
		
			Consumer findByMobileNumber = consumerRepository.findByMobileNumber(mobileNumber);
			
			if(findByMobileNumber!=null) {	
				return Response.response("Consumer Mobile Number Already Exist", HttpStatus.OK, findByMobileNumber, null);
	
			}
			Encoder encoder = Base64.getEncoder();
			String encodePass = encoder.encodeToString(password.getBytes());
			Consumer consumer=new Consumer();
			consumer.setAddress(address);
			consumer.setEmail(email);
			consumer.setMobileNumber(mobileNumber);
			consumer.setConsumerName(trimConsumerName);
			consumer.setPassword(encodePass);
			consumer.setCreatedTime(LocalDateTime.now().toString());
			consumer.setRoleId(1L);
			consumer.setIsActive(true);
			Consumer save = consumerRepository.save(consumer);

			return Response.response("Consumer Data Saved", HttpStatus.OK, save, null);

		} catch (Exception e) {
			e.printStackTrace();
			return Response.response(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, null);

		}	
		
			
	}

}
