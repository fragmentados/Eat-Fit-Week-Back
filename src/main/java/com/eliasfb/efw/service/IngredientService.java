package com.eliasfb.efw.service;

import java.util.List;

import com.eliasfb.efw.dto.CreateOrUpdateIngredientDto;
import com.eliasfb.efw.dto.IngredientDto;
import com.eliasfb.efw.dto.NutritionEstimateDto;
import com.eliasfb.efw.dto.ResponseDto;
import com.eliasfb.efw.model.FoodCategory;
import com.eliasfb.efw.model.Ingredient;

public interface IngredientService {

	ResponseDto create(CreateOrUpdateIngredientDto createIngredient);

	ResponseDto delete(int id);

	List<Ingredient> findAll();

	List<IngredientDto> findUserIngredients(Integer userId);

	IngredientDto findById(int id);

	ResponseDto update(Integer ingredientId, CreateOrUpdateIngredientDto updateIngredient);

	NutritionEstimateDto getNutritionEstimate(String ingrName);

	List<FoodCategory> getFoodCategories();
}
