package com.dsp.dsp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dsp.dsp.model.DspPaymentHistory;

@Repository
public interface DspPaymentHistoryRepository extends JpaRepository<DspPaymentHistory,Long> {

	DspPaymentHistory findByApplicationNumberAndPaymentType(String applicationNumber,String paymentType);

	DspPaymentHistory findByOrderId(String orderId);

}