package com.dsp.dsp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dsp.dsp.model.DspInvoiceHistory;

@Repository
public interface DspInvoiceHistoryRepository extends JpaRepository<DspInvoiceHistory,Long> {

	DspInvoiceHistory findByApplicationNumberAndPaymentType(String applicationNumber,String paymentType);
}
