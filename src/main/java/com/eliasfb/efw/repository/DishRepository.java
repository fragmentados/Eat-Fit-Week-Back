package com.eliasfb.efw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.eliasfb.efw.model.Dish;

public interface DishRepository extends Repository<Dish, Integer> {

	void delete(Dish dish);

	List<Dish> findAll();

	@Query("SELECT d FROM Dish d JOIN d.users u WHERE u.id = :userId")
	List<Dish> findUserDishes(@Param("userId") int userId);

	@Query("SELECT d FROM Dish d JOIN d.users u WHERE u.id = :userId AND d.name = :dishName")
	List<Dish> findByUserAndByName(@Param("userId") int userId, @Param("dishName") String dishName);

	@Query("SELECT d FROM Dish d JOIN d.users u WHERE u.id = :userId AND d.name = :dishName AND d.id != :dishId")
	List<Dish> findOtherDishByUserAndByName(@Param("userId") int userId, @Param("dishName") String dishName,
			@Param("dishId") Integer dishId);

	Dish findOne(int id);

	Dish save(Dish dish);
}
