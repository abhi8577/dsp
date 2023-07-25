package com.dsp.dsp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dsp.dsp.dto.ChangePasswordDto;
import com.dsp.dsp.dto.CredentialsDto;
import com.dsp.dsp.dto.DcAcceptOrDcChangeDto;
import com.dsp.dsp.dto.DiscomUserRegDto;
import com.dsp.dsp.response.Response;
import com.dsp.dsp.service.DiscomUserService;

@RestController
@RequestMapping("/discom_user")
public class DiscomUserController {

	@Autowired
	DiscomUserService discomUserService;

	@PostMapping("/discom_registration")
	public Response registration(@RequestBody DiscomUserRegDto discomUserRegDto) {
		return discomUserService.save(discomUserRegDto);
	}

	@PostMapping("/discom_login")
	public Response login(@RequestBody CredentialsDto credentialsDto) {
		return discomUserService.getLoginDetails(credentialsDto);
	}

	@PostMapping("/change_discom_password")
	public Response changePwd(@RequestBody ChangePasswordDto changePasswordDto) {
		return discomUserService.changePwd(changePasswordDto);
	}

	@GetMapping("/discom_user_details/{mobileNo}")
	public Response dsicomUserDetails(@PathVariable(name="mobileNo") String mobileNo) {
		return discomUserService.dsicomUserDetails(mobileNo);
	}

	@GetMapping("/application_details_by_dc_for_discom_user/{dcId}")
	public Response applicationDetailsByDcForDiscomUser(@PathVariable("dcId") Long dcId) {
		return discomUserService.applicationDetailsByDcForDiscomUser(dcId);
	}
	
	@PostMapping("/accept_app_or_change_dc")
	public Response acceptAppOrChangeDc(@RequestBody DcAcceptOrDcChangeDto dcAcceptOrDcChangeDto) {
		return discomUserService.acceptAppOrChangeDc(dcAcceptOrDcChangeDto);
	}
	
	@PostMapping("/erp_survey_submit")
	public Response erpSurveySubmit(@RequestPart String erpSurveySubmitDto,
			@RequestPart(value="eRPEstimateFile",required = true) MultipartFile eRPEstimateFile) {
				return discomUserService.erpSurveySubmit(erpSurveySubmitDto,eRPEstimateFile);
	
	}
}