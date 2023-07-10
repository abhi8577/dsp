package com.dsp.dsp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dsp.dsp.model.Consumer;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Long>{
	
	Consumer findByMobileNumber(String mobileNumber);

}
