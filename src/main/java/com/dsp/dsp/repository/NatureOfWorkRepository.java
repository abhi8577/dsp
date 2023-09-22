package com.dsp.dsp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.dsp.dsp.model.NatureOfWork;

public interface NatureOfWorkRepository extends JpaRepository<NatureOfWork, Long> {

	List<NatureOfWork> findAllByNatureOfWorkIdIn(List<Long> list);
}