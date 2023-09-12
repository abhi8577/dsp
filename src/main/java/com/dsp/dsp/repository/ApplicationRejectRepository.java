package com.dsp.dsp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dsp.dsp.dto.ApplicationRejectDto;
import com.dsp.dsp.model.ApplicationRejectedDetails;

public interface ApplicationRejectRepository extends JpaRepository<ApplicationRejectedDetails, Long>{

}
