package com.dsp.dsp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dsp.dsp.model.SubDivision;

public interface SubDivisionRepository extends JpaRepository<SubDivision, Long> {

	List<SubDivision> findByDivisionId(Long divisionId);
}
