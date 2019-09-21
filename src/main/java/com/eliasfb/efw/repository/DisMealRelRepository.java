package com.eliasfb.efw.repository;

import org.springframework.data.repository.Repository;

import com.eliasfb.efw.model.DisMealRel;

public interface DisMealRelRepository extends Repository<DisMealRel, Integer> {
	void delete(DisMealRel disMealRel);
}
