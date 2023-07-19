package com.dsp.dsp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;

import com.dsp.dsp.response.Response;
import com.dsp.dsp.service.ConsumerApplicationService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/consumer_application")
public class ConsumerApplicationController {

	@Autowired
	ConsumerApplicationService consumerApplicationService;

	@PostMapping("/submit")
	public Response consumerApplicationFormSubmit(@RequestPart String consumerApplicationDto,
			@RequestPart(value="TAndCPpermissionFile",required = false) MultipartFile TAndCPpermissionFile,
			@RequestPart(value="reraPermissionFile",required = false) MultipartFile reraPermissionFile,
			@RequestPart(value="grouppermissionFile",required = false) MultipartFile grouppermissionFile,
			@RequestPart(value="registryFile",required = false) MultipartFile registryFile ,
			@RequestPart(value="NOCfile",required = false) MultipartFile NOCfile,
			@RequestPart(value="administrativeFile",required = false) MultipartFile administrativeFile,
			@RequestPart(value="gstFile",required = false) MultipartFile gstFile,
			@RequestPart(value="khasraKhatoniFile",required = false) MultipartFile khasraKhatoniFile ) {


		return consumerApplicationService.submit(consumerApplicationDto,TAndCPpermissionFile,reraPermissionFile,grouppermissionFile,registryFile,NOCfile,administrativeFile,gstFile,khasraKhatoniFile);
	}

	@PostMapping("/update")
	public Response consumerApplicationFormUpdate(@RequestPart String consumerApplicationDto,
			@RequestPart(value="TAndCPpermissionFile",required = false) MultipartFile TAndCPpermissionFile,
			@RequestPart(value="reraPermissionFile",required = false) MultipartFile reraPermissionFile,
			@RequestPart(value="grouppermissionFile",required = false) MultipartFile grouppermissionFile,
			@RequestPart(value="registryFile",required = false) MultipartFile registryFile ,
			@RequestPart(value="NOCfile",required = false) MultipartFile NOCfile,
			@RequestPart(value="administrativeFile",required = false) MultipartFile administrativeFile,
			@RequestPart(value="gstFile",required = false) MultipartFile gstFile,
			@RequestPart(value="khasraKhatoniFile",required = false) MultipartFile khasraKhatoniFile ) {

		return consumerApplicationService.update(consumerApplicationDto,TAndCPpermissionFile,reraPermissionFile,grouppermissionFile,registryFile,NOCfile,administrativeFile,gstFile,khasraKhatoniFile);
	}

	@GetMapping("/pending_for_geo_location/{mobileNo}")
	public Response pendingForGeoLocationApplication(@PathVariable(name="mobileNo") String mobileNo) {
		return consumerApplicationService.pendingForGeoLocationApplication(mobileNo);
	}

	@PostMapping("/geo_location_add")
	public Response addGeoLocation(@RequestPart String geoLocationAddForm,
			@RequestPart(value="startDocPath",required = false) MultipartFile startDocPath,
			@RequestPart(value="endDocPath",required = false) MultipartFile endDocPath) {

		return consumerApplicationService.addGeoLocation(geoLocationAddForm,startDocPath,endDocPath);

	}
	
	@GetMapping("/get_consumer_applications/{mobileNo}")
	public Response getConsumerApplications(@PathVariable(name="mobileNo") String mobileNo) {
		return consumerApplicationService.getConsumerApplications(mobileNo);
	}
}