package com.dsp.dsp.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
}
