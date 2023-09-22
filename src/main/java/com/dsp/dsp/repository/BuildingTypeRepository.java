package com.dsp.dsp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dsp.dsp.model.BuildingType;

@Repository
public interface BuildingTypeRepository extends JpaRepository<BuildingType, Long> {

}
