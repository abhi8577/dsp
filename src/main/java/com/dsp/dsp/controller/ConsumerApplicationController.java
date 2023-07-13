package com.dsp.dsp.controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer_application")
public class ConsumerApplicationController {

	@PostMapping("/submit")
	public Response consumerApplicationForm(@RequestPart String consumerApplicationDto,
			@RequestPart(value="TAndCPpermissionFile",required = false) MultipartFile TAndCPpermissionFile,
			@RequestPart(value="reraPermissionFile",required = false) MultipartFile reraPermissionFile,
			@RequestPart(value="grouppermissionFile",required = false) MultipartFile grouppermissionFile,
			@RequestPart(value="registryFile",required = false) MultipartFile registryFile ,
			@RequestPart(value="NOCfile",required = false) MultipartFile NOCfile,
			@RequestPart(value="administrativeFile",required = false) MultipartFile administrativeFile,
			@RequestPart(value="gstFile",required = false) MultipartFile gstFile,
			@RequestPart(value="khasraKhatoniFile",required = false) MultipartFile khasraKhatoniFile ) {
		

		submit();
}
