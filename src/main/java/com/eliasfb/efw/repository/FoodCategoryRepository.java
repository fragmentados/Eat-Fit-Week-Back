package com.eliasfb.efw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.eliasfb.efw.model.FoodCategory;

public interface FoodCategoryRepository extends Repository<FoodCategory, Integer> {

	@Query("SELECT cat FROM FoodCategory cat WHERE cat.id IN :ids")
	List<FoodCategory> findByIds(@Param("ids") List<Integer> ids);

	List<FoodCategory> findAll();
}
