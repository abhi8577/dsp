package com.dsp.dsp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dsp.dsp.dto.ApplicationRejectDto;
import com.dsp.dsp.dto.ConsumerApplicationIdDto;
import com.dsp.dsp.dto.ConsumerApplicationUpdateDto;
import com.dsp.dsp.dto.DtrPtrDto;
import com.dsp.dsp.response.Response;
import com.dsp.dsp.service.ConsumerApplicationService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/consumer_application")
public class ConsumerApplicationController {

	@Autowired
	ConsumerApplicationService consumerApplicationService;

	@PostMapping("/submit")
	public Response consumerApplicationFormSubmit(@RequestPart String consumerApplicationDto,
			@RequestPart(value = "TAndCPpermissionFile", required = false) MultipartFile TAndCPpermissionFile,
			@RequestPart(value = "reraPermissionFile", required = false) MultipartFile reraPermissionFile,
			@RequestPart(value = "grouppermissionFile", required = false) MultipartFile grouppermissionFile,
			@RequestPart(value = "registryFile", required = false) MultipartFile registryFile,
			@RequestPart(value = "NOCfile", required = false) MultipartFile NOCfile,
			@RequestPart(value = "administrativeFile", required = false) MultipartFile administrativeFile,
			@RequestPart(value = "gstFile", required = false) MultipartFile gstFile,
			@RequestPart(value = "khasraKhatoniFile", required = false) MultipartFile khasraKhatoniFile) {

		return consumerApplicationService.submit(consumerApplicationDto, TAndCPpermissionFile, reraPermissionFile,
				grouppermissionFile, registryFile, NOCfile, administrativeFile, gstFile, khasraKhatoniFile);
	}

	@PostMapping("/update")
	public Response consumerApplicationFormUpdate(@RequestPart String consumerApplicationDto,
			@RequestPart(value = "TAndCPpermissionFile", required = false) MultipartFile TAndCPpermissionFile,
			@RequestPart(value = "reraPermissionFile", required = false) MultipartFile reraPermissionFile,
			@RequestPart(value = "grouppermissionFile", required = false) MultipartFile grouppermissionFile,
			@RequestPart(value = "registryFile", required = false) MultipartFile registryFile,
			@RequestPart(value = "NOCfile", required = false) MultipartFile NOCfile,
			@RequestPart(value = "administrativeFile", required = false) MultipartFile administrativeFile,
			@RequestPart(value = "gstFile", required = false) MultipartFile gstFile,
			@RequestPart(value = "khasraKhatoniFile", required = false) MultipartFile khasraKhatoniFile) {

		return consumerApplicationService.update(consumerApplicationDto, TAndCPpermissionFile, reraPermissionFile,
				grouppermissionFile, registryFile, NOCfile, administrativeFile, gstFile, khasraKhatoniFile);
	}

	@GetMapping("/pending_for_geo_location/{mobileNo}")
	public Response pendingForGeoLocationApplication(@PathVariable(name = "mobileNo") String mobileNo) {
		return consumerApplicationService.pendingForGeoLocationApplication(mobileNo);
	}

	@PostMapping("/geo_location_add")
	public Response addGeoLocation(@RequestPart String geoLocationAddForm,
			@RequestPart(value = "startDocPath", required = false) MultipartFile startDocPath,
			@RequestPart(value = "endDocPath", required = false) MultipartFile endDocPath) {

		return consumerApplicationService.addGeoLocation(geoLocationAddForm, startDocPath, endDocPath);
	}

	@PostMapping("/get_registration_fee_payment_detail_by_application_number")
	public Response getRegistrationFeePaymentDetailByConsumerApplicationNumber(
			@RequestBody ConsumerApplicationIdDto consumerApplicationIdDto) {
		return consumerApplicationService
				.getRegistrationFeePaymentDetailByConsumerApplicationNumber(consumerApplicationIdDto);
	}

	@GetMapping("/get_consumer_applications/{mobileNo}")
	public Response getConsumerApplications(@PathVariable(name = "mobileNo") String mobileNo) {
		return consumerApplicationService.getConsumerApplications(mobileNo);
	}

	@PostMapping("/update_dtr_ptr")
	public Response updateDtrPtr(@RequestBody DtrPtrDto dtrPtrDto) {
		return consumerApplicationService.updateDtrPtr(dtrPtrDto);
	}

	@PostMapping("/reject_application")
	public Response rejectApplication(@RequestBody ApplicationRejectDto applicationRejectDto) {
		return consumerApplicationService.rejectApplication(applicationRejectDto);
	}

	@PostMapping("/consumerApplicationUpdateByMKMY")
	public Response consumerApplicationUpdateByMKMY(@RequestPart String consumerApplicationUpdateDto,
			@RequestPart(value = "khasraFile", required = false) MultipartFile khasraFile,
			@RequestPart(value = "samagraFile", required = false) MultipartFile samagraFile) {

		return consumerApplicationService.consumerApplicationUpdateByMKMY(consumerApplicationUpdateDto, khasraFile,
				samagraFile);
	}
}