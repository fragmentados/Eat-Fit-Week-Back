package com.eliasfb.efw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.eliasfb.efw.model.Ingredient;

public interface IngredientRepository extends Repository<Ingredient, Integer> {

	void delete(Ingredient ingredient);

	List<Ingredient> findAll();

	@Query("SELECT i FROM Ingredient i JOIN i.users u WHERE u.id = :userId ORDER BY i.category")
	List<Ingredient> findByUserId(@Param("userId") int userId);

	@Query("SELECT i FROM Ingredient i JOIN i.users u WHERE u.id = :userId ORDER BY i.name")
	List<Ingredient> findByUserIdSortedByName(@Param("userId") int userId);

	@Query("SELECT i FROM Ingredient i JOIN i.users u WHERE u.id = :userId AND i.name = :ingrName")
	List<Ingredient> findByUserIdAndName(@Param("userId") int userId, @Param("ingrName") String name);

	@Query("SELECT i FROM Ingredient i JOIN i.users u WHERE u.id = :userId AND i.name = :ingrName AND i.id != :ingredientId")
	List<Ingredient> findOtherIngredientByUserIdAndName(@Param("userId") int userId, @Param("ingrName") String name,
			@Param("ingredientId") Integer ingredientId);

	Ingredient findOne(int id);

	Ingredient save(Ingredient ingredient);
}
