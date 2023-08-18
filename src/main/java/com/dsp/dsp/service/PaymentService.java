package com.dsp.dsp.service;

import javax.servlet.http.HttpServletRequest;

import com.dsp.dsp.dto.DemandFeeCalculationResponseDto;
import com.dsp.dsp.response.Response;

public interface PaymentService {

	Response createOrder(String consumerAppNo, String paymentType, HttpServletRequest httpServletRequest);

	Response savePaymentResponse(HttpServletRequest httpServletRequest) throws Exception ;

	Response createDemandOrder(DemandFeeCalculationResponseDto demandFeeCalculationDto, String paymentType,
			HttpServletRequest httpServletRequest);
}