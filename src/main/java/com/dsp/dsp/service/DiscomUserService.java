package com.dsp.dsp.service;

import org.springframework.web.multipart.MultipartFile;

import com.dsp.dsp.dto.ChangePasswordDto;
import com.dsp.dsp.dto.CredentialsDto;
import com.dsp.dsp.dto.DcAcceptOrDcChangeDto;
import com.dsp.dsp.dto.DiscomUserRegDto;
import com.dsp.dsp.response.Response;

public interface DiscomUserService {
	
	Response save(DiscomUserRegDto discomUserRegDto);
	
	Response getLoginDetails(CredentialsDto credentialsDto);

	Response changePwd(ChangePasswordDto changePasswordDto);

	Response dsicomUserDetails(String mobileNo);

	Response applicationDetailsByDcForDiscomUser(Long dcId);

	Response acceptAppOrChangeDc(DcAcceptOrDcChangeDto dcAcceptOrDcChangeDto);

	Response erpSurveySubmit(String erpSurveySubmitDto, MultipartFile eRPEstimateFile);

	Response demandFeeCalculation(String consumerAppNo);
}