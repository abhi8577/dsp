package com.dsp.dsp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dsp.dsp.model.AccessLevel;

@Repository
public interface AccessLevelRepository extends JpaRepository<AccessLevel, Long> {

}
