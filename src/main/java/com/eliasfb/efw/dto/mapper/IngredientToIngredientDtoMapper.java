package com.eliasfb.efw.dto.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;

import com.eliasfb.efw.dto.CreateOrUpdateIngredientDto;
import com.eliasfb.efw.dto.FoodCategoryDto;
import com.eliasfb.efw.dto.IngredientDto;
import com.eliasfb.efw.dto.NutritionEstimateDto;
import com.eliasfb.efw.dto.stat.StatDto;
import com.eliasfb.efw.enums.NutritionalStatEnum;
import com.eliasfb.efw.model.FoodCategory;
import com.eliasfb.efw.model.Ingredient;
import com.eliasfb.efw.model.User;
import com.eliasfb.efw.service.rest.response.NutritionData;

@Mapper(componentModel = "spring")
public abstract class IngredientToIngredientDtoMapper {

	public abstract Ingredient createIngredientDtoToIngredient(CreateOrUpdateIngredientDto createIngredient);

	public Ingredient createToEntity(CreateOrUpdateIngredientDto dto) {
		Ingredient ingredient = createIngredientDtoToIngredient(dto);
		if (dto.getUserId() != null) {
			User user = new User();
			user.setId(dto.getUserId());
			ingredient.setUsers(new HashSet<>(Arrays.asList(user)));
		}
		if (dto.getCategoryId() != null) {
			ingredient.setCategory(new FoodCategory(dto.getCategoryId()));
		}
		return ingredient;
	}

	public List<IngredientDto> ingredientToDtoList(List<Ingredient> ingredient) {
		return ingredient.stream().map(i -> this.toDto(i)).collect(Collectors.toList());
	}

	protected abstract IngredientDto ingredientToDto(Ingredient ingredient);

	public IngredientDto toDto(Ingredient ing) {
		IngredientDto dto = ingredientToDto(ing);
		dto.setStats(getIngredientStats(ing));
		return dto;
	}

	public NutritionEstimateDto nutritionResponseToDto(NutritionData nutritionData) {
		return new NutritionEstimateDto(getNutritionDataStats(nutritionData));
	}

	public List<StatDto> getNutritionDataStats(NutritionData nutritionData) {
		List<StatDto> stats = new ArrayList<>();
		stats.add(new StatDto(NutritionalStatEnum.CALORIES.getName(),
				getStatQuantityFromNutritionData(nutritionData, NutritionData.CALORIES_MAP_KEY)));
		stats.add(new StatDto(NutritionalStatEnum.FATS.getName(),
				getStatQuantityFromNutritionData(nutritionData, NutritionData.FAT_MAP_KEY)));
		stats.add(new StatDto(NutritionalStatEnum.PROTEINS.getName(),
				getStatQuantityFromNutritionData(nutritionData, NutritionData.PROTEINS_MAP_KEY)));
		return stats;
	}

	private String getStatQuantityFromNutritionData(NutritionData nutritionData, String key) {
		Double quantityResponse = nutritionData.getTotalNutrients().get(key).getQuantity();
		// Turn to quantity / 100 gr
		quantityResponse = quantityResponse / nutritionData.getTotalWeight() * 100;
		// Round to two decimals
		quantityResponse = Math.round(quantityResponse * 100.0) / 100.0;
		return String.valueOf(quantityResponse);
	}

	public List<StatDto> getIngredientStats(Ingredient ingredient) {
		List<StatDto> stats = new ArrayList<>();
		stats.add(new StatDto(NutritionalStatEnum.CALORIES.getName(), String.valueOf(ingredient.getCalories())));
		stats.add(new StatDto(NutritionalStatEnum.FATS.getName(), String.valueOf(ingredient.getFats())));
		stats.add(new StatDto(NutritionalStatEnum.PROTEINS.getName(), String.valueOf(ingredient.getProteins())));
		stats.add(new StatDto(NutritionalStatEnum.CARBOHYDRATES.getName(),
				String.valueOf(ingredient.getCarbohydrates())));
		return stats;
	}

	public abstract List<FoodCategoryDto> foodCategoryListToDto(List<FoodCategory> categories);

}
