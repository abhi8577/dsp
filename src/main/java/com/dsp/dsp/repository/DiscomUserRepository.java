package com.dsp.dsp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dsp.dsp.model.Consumer;
import com.dsp.dsp.model.DiscomUser;

@Repository
public interface DiscomUserRepository extends JpaRepository<DiscomUser, Long> {
	
	DiscomUser findByUserId(String userId);

}