package com.dsp.dsp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dsp.dsp.model.Division;

@Repository
public interface DivisionRepository extends JpaRepository<Division,Long> {

	List<Division> findByCircleId(Long circleId);
	
	Division findByDivId(Long divId);

}