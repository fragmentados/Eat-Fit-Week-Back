package com.eliasfb.efw.service.rest.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.eliasfb.efw.service.rest.IngredientRestService;
import com.eliasfb.efw.service.rest.response.NutritionData;

@Service
public class IngredientRestServiceImpl implements IngredientRestService {

	private static final String NUTRITION_WS_HOST = "api.edamam.com";
	private static final String NUTRITION_WS_APP_ID = "5094d310";
	private static final String NUTRITION_WS_APP_KEY = "07c1f9f76c2135cfee70536721ea71aa";

	@Override
	public NutritionData callNutritionService(String ingredientName) {
		// We prepare the request
		String request = "https://" + NUTRITION_WS_HOST + "/api/nutrition-data";
		String queryParams = "?app_id=" + NUTRITION_WS_APP_ID + "&app_key=" + NUTRITION_WS_APP_KEY + "&ingr=1 small "
				+ ingredientName;
		// We execute the request
		RestTemplate template = new RestTemplate();
		ResponseEntity<NutritionData> response = template.getForEntity(request + queryParams, NutritionData.class);
		// We return the result
		return response != null ? response.getBody() : null;
	}
}
