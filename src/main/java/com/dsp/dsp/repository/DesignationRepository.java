package com.dsp.dsp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dsp.dsp.model.Designation;

@Repository
public interface DesignationRepository extends JpaRepository<Designation, Long> {

}
