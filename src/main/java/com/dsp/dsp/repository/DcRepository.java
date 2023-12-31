package com.dsp.dsp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dsp.dsp.model.Dc;

public interface DcRepository extends JpaRepository<Dc, Long>{
	
	Dc findByDcId(Long dcId);
	
	List<Dc> findBySubDivId(Long subDivId);

	List<Dc> findByDistrictId(Long districtId);
}
