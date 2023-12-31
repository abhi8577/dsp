package com.dsp.dsp.serviceImpl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dsp.dsp.dto.ChangePasswordDto;
import com.dsp.dsp.dto.ConsumerRegDto;
import com.dsp.dsp.dto.CredentialsDto;
import com.dsp.dsp.model.Consumer;
import com.dsp.dsp.repository.ConsumerRepository;
import com.dsp.dsp.response.Response;
import com.dsp.dsp.service.ConsumerService;
import com.dsp.dsp.util.Utility;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

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

			if (mobileNumber == null || mobileNumber.isEmpty()) {
				return Response.response("Mobile number should not be null", HttpStatus.BAD_REQUEST, null, null);
			}
			//isValidPhoneNumber(mobileNumber);

			if (password == null || password.isEmpty()) {
				return Response.response("Password should not be null", HttpStatus.BAD_REQUEST, null, null);

			}

			if (consumerName == null || consumerName.isEmpty()) {
				return Response.response("Consumer name should not be null", HttpStatus.BAD_REQUEST, null, null);
			}
			
			if (email == null || email.isEmpty()) {
				return Response.response("Email id should not be null", HttpStatus.BAD_REQUEST, null, null);

			}
			if (address == null || address.isEmpty()) {
				return Response.response("Address should not be null", HttpStatus.BAD_REQUEST, null, null);

			}

			String trimConsumerName = consumerName.replaceAll("\\s+", " ").trim().toUpperCase();

			Consumer findByMobileNumber = consumerRepository.findByMobileNumber(mobileNumber);

			if (findByMobileNumber != null) {
				return Response.response("Consumer Mobile Number Already Exist", HttpStatus.OK, findByMobileNumber,
						null);

			}

			String encodePass = Utility.getEncodeData(password);

			Consumer consumer = new Consumer();
			consumer.setAddress(address);
			consumer.setEmail(email);
			consumer.setMobileNumber(mobileNumber);
			consumer.setConsumerName(trimConsumerName);
			consumer.setPassword(encodePass);
			consumer.setCreatedTime(LocalDateTime.now().toString());
			consumer.setRoleId(27L);
			consumer.setIsActive(true);
			Consumer save = consumerRepository.save(consumer);

			return Response.response("Consumer Data Saved", HttpStatus.OK, save, null);

		} catch (Exception e) {
			e.printStackTrace();
			return Response.response(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, null);

		}

	}

	@Override
	public Response getLoginDetails(CredentialsDto credentialsDto) {

		try {
			String id = credentialsDto.getId();
			String password = credentialsDto.getPassword();

			if (id == null || id.isEmpty()) {
				return Response.response("Id should not be null", HttpStatus.BAD_REQUEST, null, null);
			}

			if (password == null || password.isEmpty()) {
				return Response.response("Password should not be null", HttpStatus.BAD_REQUEST, null, null);
			}

			Consumer findByMobileNumber = consumerRepository.findByMobileNumber(id);

			if (findByMobileNumber == null) {
				return Response.response("Consumer not found", HttpStatus.NOT_FOUND, null, null);
			}
			String decryptData = Utility.getDecryptData(findByMobileNumber.getPassword());

			if (decryptData.equals(password)) {
				
				 //String jwtToken = generateJWTToken(findByMobileNumber.getConsumerName());
				return Response.response("Login successfully", HttpStatus.OK, findByMobileNumber, null);
			} else {
				return Response.response("Invalid credentials", HttpStatus.NOT_FOUND, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Response.response(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, null);

		}
	}

	  // Method to generate JWT token
    private String generateJWTToken(String userId) {
        // Set expiration time for the token (e.g., 1 hour)
        long expirationTimeMillis = System.currentTimeMillis() + 3600000;  // 1 hour

        byte[] keyBytes = Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded();

        // Create the JWT token
        String jwtToken = Jwts.builder()
                .setSubject(userId)
                .setExpiration(new Date(expirationTimeMillis))
                .signWith(SignatureAlgorithm.HS256, keyBytes)
                .compact();

        return jwtToken;
    }
    
	
	@Override
	public Response changePwd(ChangePasswordDto changePasswordDto) {
		try {
			Optional<Consumer> consumer = consumerRepository.findById(changePasswordDto.getId());
			if (consumer.isPresent()) {

				Consumer consumer2 = consumer.get();
				String encodePass = Utility.getEncodeData(changePasswordDto.getPassword());
				consumer2.setPassword(encodePass);

				consumer2 = consumerRepository.save(consumer2);

				return Response.response("Password Successfully changed", HttpStatus.OK, consumer2, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return Response.response("Id Not Found", HttpStatus.NOT_FOUND, null, null);
	}

	@Override
	public Response consumerDetails(String mobileNo) {

		Consumer consumer = consumerRepository.findByMobileNumber(mobileNo);

		if (consumer == null) {
			return Response.response("Consumer not found", HttpStatus.NOT_FOUND, null, null);
		}
		return Response.response("Consumer Details", HttpStatus.OK, consumer, null);
	}
	
	
//	public static String isValidPhoneNumber(String phoneNumber) {
//	        // Check if the phone number is exactly 10 digits and consists of only numeric characters
//	      if(phoneNumber.length() != 10) {
//	   	        return "Please enter 10 digit number";
//	      }
//	      if(!phoneNumber.matches("\\d+")) {
//	   	        return "Please enter only number";
//
//	      }
//		
//	    }
}