package com.dsp.dsp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dsp.dsp.model.ERPEstimate;

@Repository
public interface ERPEstimateRepository extends JpaRepository<ERPEstimate, Long> {

	ERPEstimate findByProjectNumber(String projectNumber);
}