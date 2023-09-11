package com.dsp.dsp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dsp.dsp.model.Substation;

@Repository
public interface SubStationRepository extends JpaRepository<Substation, Long> {

	List<Substation> findByDcId(Long dcId);

}