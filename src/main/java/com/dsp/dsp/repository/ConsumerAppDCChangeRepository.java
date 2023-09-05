package com.dsp.dsp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dsp.dsp.model.ConsumerAppDCChange;

@Repository
public interface ConsumerAppDCChangeRepository extends JpaRepository<ConsumerAppDCChange, Long> {

}