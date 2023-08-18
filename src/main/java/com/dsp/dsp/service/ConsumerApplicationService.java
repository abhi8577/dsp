package com.dsp.dsp.service;

import org.springframework.web.multipart.MultipartFile;

import com.dsp.dsp.dto.ConsumerApplicationIdDto;
import com.dsp.dsp.dto.DtrPtrDto;
import com.dsp.dsp.response.Response;

public interface ConsumerApplicationService {
	
	Response submit(String consumerApplicationDto, MultipartFile tAndCPpermissionFile, MultipartFile reraPermissionFile, MultipartFile grouppermissionFile, MultipartFile registryFile, MultipartFile nOCfile, MultipartFile administrativeFile, MultipartFile gstFile, MultipartFile khasraKhatoniFile);

	
	Response update(String consumerApplicationDto, MultipartFile tAndCPpermissionFile, MultipartFile reraPermissionFile, MultipartFile grouppermissionFile, MultipartFile registryFile, MultipartFile nOCfile, MultipartFile administrativeFile, MultipartFile gstFile, MultipartFile khasraKhatoniFile);

	Response pendingForGeoLocationApplication(String mobileNo);

	Response addGeoLocation(String geoLocationAddForm, MultipartFile startDocPath, MultipartFile endDocPath);

	Response getRegistrationFeePaymentDetailByConsumerApplicationNumber(ConsumerApplicationIdDto consumerApplicationIdDto);

	Response getConsumerApplications(String mobileNo);
	
	Response updateDtrPtr (DtrPtrDto dtrPtrDto);


}