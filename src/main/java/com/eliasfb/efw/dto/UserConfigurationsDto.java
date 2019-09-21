package com.eliasfb.efw.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserConfigurationsDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Double maxCaloriesPerWeek;
	private Double maxProteinsPerWeek;
	private Double maxFatsPerWeek;
	private Double maxCarbohydratesPerWeek;
	private List<FoodCategoryDto> bannedCategories;
	private List<MealDto> meals;
}
