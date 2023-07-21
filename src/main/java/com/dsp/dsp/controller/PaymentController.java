package com.dsp.dsp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dsp.dsp.response.Response;
import com.dsp.dsp.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	PaymentService paymentService;

	@PostMapping("/make_registration_payment/{consumerAppNo}/{paymentType}")
	public Response createOrder(@PathVariable(name="consumerAppNo") String consumerAppNo,
			@PathVariable(name="paymentType") String paymentType,HttpServletRequest httpServletRequest) {		
		return paymentService.createOrder(consumerAppNo,paymentType,httpServletRequest);		
	}

	@PostMapping(value = "/billdesk_payment_response", consumes = { "application/x-www-form-urlencoded;charset=UTF-8" } )
	public Response savePaymentResponse(HttpServletRequest httpServletRequest) throws Exception  {
		return paymentService.savePaymentResponse(httpServletRequest);
	}
}