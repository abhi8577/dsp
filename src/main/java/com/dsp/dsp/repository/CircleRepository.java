package com.dsp.dsp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dsp.dsp.model.Circle;

@Repository
public interface CircleRepository extends JpaRepository<Circle, Long> {
	
	List<Circle> findByRegionCode(Long regionCode);

	Circle findByCircleId(Long circleId);
}