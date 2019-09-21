package com.eliasfb.efw.service.rest;

import com.eliasfb.efw.service.rest.response.NutritionData;

public interface IngredientRestService {
	NutritionData callNutritionService(String ingredientName);
}
