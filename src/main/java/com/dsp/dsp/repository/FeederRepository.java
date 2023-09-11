package com.dsp.dsp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dsp.dsp.model.Feeder;

@Repository
public interface FeederRepository extends JpaRepository<Feeder, Long> {

	List<Feeder> findBySubStationId(Long subStationId);

}