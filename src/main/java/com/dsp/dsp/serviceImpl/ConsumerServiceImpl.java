package com.dsp.dsp.serviceImpl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dsp.dsp.model.Consumer;
import com.dsp.dsp.repository.ConsumerRepository;
import com.dsp.dsp.response.Response;
import com.dsp.dsp.service.ConsumerService;

@Service
public class ConsumerServiceImpl implements ConsumerService {

	@Autowired
	private ConsumerRepository consumerRepository;
	
	@Override
	public Response save(Consumer consumer) {
		// TODO Auto-generated method stub
		
		Consumer findByMobileNumber = consumerRepository.findByMobileNumber(consumer.getMobileNumber());
		
		if(findByMobileNumber!=null) {
			return Response.response("Consumer Mobile Number Already Exist", HttpStatus.OK,
					findByMobileNumber, null);	
		}
		consumer.setCreatedTime(LocalDateTime.now());
		consumer.setRoleId(1L);
		Consumer save = consumerRepository.save(consumer);
		
		
		return Response.response("Consumer Data Saved", HttpStatus.OK,
				save, null);	
	}

}
