package com.dsp.dsp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dsp.dsp.model.ConsumerApplication;

public interface ConsumerApplicationRepository extends JpaRepository<ConsumerApplication, Long> {

	
	@Query(value="select * from CONSUMER_APPLICATION where CONSUMER_ID=:cId and CONSUMER_APPLICATION_ID=:caId",nativeQuery = true )
	 public ConsumerApplication getConsumerApplicationByConsumerIdAndConsumerApplicationNumber(@Param("cId") Long consumerId , @Param("caId") String consumerApplicationId);

}
