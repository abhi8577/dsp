package com.dsp.dsp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dsp.dsp.model.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long>{

}
