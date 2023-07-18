package com.dsp.dsp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dsp.dsp.model.Consumer;

@Repository
public interface ConsumerRepository extends JpaRepository<Consumer, Long>{
	
	Consumer findByMobileNumber(String mobileNumber);
	
//	@Query(value="select * from MOBILE_NUMBER=:mNo and PASSWORD=:pass",nativeQuery = true)
//	public Consumer getLoginDetailByMobileAndPassword(@Param("mNo") String mobile,@Param("pass") String password)	;

}