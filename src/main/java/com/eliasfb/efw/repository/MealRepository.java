package com.eliasfb.efw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.eliasfb.efw.model.Meal;

public interface MealRepository extends Repository<Meal, Integer> {

	@Query("SELECT m FROM Meal m JOIN m.user u WHERE u.id = :userId ORDER BY m.hour")
	List<Meal> findByUserIdOrderByHour(@Param("userId") Integer userId);

	List<Meal> findByUserIdAndHour(Integer userId, String hour);

	void delete(Meal meal);

	Meal save(Meal meal);

	Meal findOne(Integer id);
}
