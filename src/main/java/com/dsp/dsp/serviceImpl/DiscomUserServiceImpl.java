package com.dsp.dsp.serviceImpl;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Base64.Encoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dsp.dsp.dto.DiscomUserRegDto;
import com.dsp.dsp.model.DiscomUser;
import com.dsp.dsp.repository.DiscomUserRepository;
import com.dsp.dsp.response.Response;
import com.dsp.dsp.service.DiscomUserService;

@Service
public class DiscomUserServiceImpl implements DiscomUserService{

	@Autowired
	DiscomUserRepository discomUserRepository;
	
	@Override
	public Response save(DiscomUserRegDto discomUserRegDto) {
	try {
			
			if (discomUserRegDto == null) {
 
	      return Response.response("User request should not be null", HttpStatus.BAD_REQUEST, null, null);
			
			}
			
			String accessLevel = discomUserRegDto.getAccessLevel();
			Long circleId = discomUserRegDto.getCircleId();
			Long dcId = discomUserRegDto.getDcId();
			Long designationId = discomUserRegDto.getDesignationId();
			Long discomId = discomUserRegDto.getDiscomId();
			Long divId = discomUserRegDto.getDivId();
			String email = discomUserRegDto.getEmail();
			Long feederId = discomUserRegDto.getFeederId();
			String mobileNo = discomUserRegDto.getMobileNo();
			String password = discomUserRegDto.getPassword();
			Long regionId = discomUserRegDto.getRegionId();
			Long subDivisionId = discomUserRegDto.getSubDivisionId();
			Long substationId = discomUserRegDto.getSubstationId();
			String userId = discomUserRegDto.getUserId();
			String userName = discomUserRegDto.getUserName();
			
			if(userId==null || userId.isEmpty()) {

				return Response.response("User id should not be null", HttpStatus.BAD_REQUEST, null, null);

				}
			
			if(mobileNo==null || mobileNo.isEmpty()) {
				return Response.response("Mobile number should not be null", HttpStatus.BAD_REQUEST, null, null);

			}
			
			if(password==null || password.isEmpty()) {	
				return Response.response("Password should not be null", HttpStatus.BAD_REQUEST, null, null);
	
			}
			
			if(userName==null || userName.isEmpty()) {

		return Response.response("User name should not be null", HttpStatus.BAD_REQUEST, null, null);

			}	
			
			
			
			if(accessLevel==null || accessLevel.isEmpty()) {
				return Response.response("Access level should not be null", HttpStatus.BAD_REQUEST, null, null);
		
			}
			
			String trimUserName = userName.replaceAll("\\s+", " ").trim().toUpperCase();
		
		 DiscomUser findByUserId = discomUserRepository.findByUserId(userId);
			
			if(findByUserId!=null) {	
				return Response.response("User id already exist", HttpStatus.OK, findByUserId, null);
	
			}
			Encoder encoder = Base64.getEncoder();
			String encodePass = encoder.encodeToString(password.getBytes());
			DiscomUser discomUser=new DiscomUser();
			discomUser.setAccessLevel(accessLevel);
			discomUser.setCircleId(circleId);
			discomUser.setCreatedTime(LocalDateTime.now().toString());
			discomUser.setDcId(dcId);
			discomUser.setDesignationId(designationId);
			discomUser.setDiscomId(discomId);
			discomUser.setDivId(divId);
			discomUser.setEmail(email);
			discomUser.setFeederId(feederId);
			discomUser.setIsActive(true);
			discomUser.setMobileNo(mobileNo);
			discomUser.setPassword(encodePass);
			discomUser.setRegionId(regionId);
			discomUser.setSubDivisionId(subDivisionId);
			discomUser.setSubstationId(substationId);
			discomUser.setUserId(userId);
			discomUser.setUserName(trimUserName);
			
		DiscomUser save = discomUserRepository.save(discomUser);

			return Response.response("Discom user data saved", HttpStatus.OK, save, null);

		} catch (Exception e) {
			e.printStackTrace();
			return Response.response(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null, null);

		}
	}

}
