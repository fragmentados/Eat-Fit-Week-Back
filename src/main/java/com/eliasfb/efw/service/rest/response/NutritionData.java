package com.eliasfb.efw.service.rest.response;

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class NutritionData implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String PROTEINS_MAP_KEY = "PROCNT";
	public static final String FAT_MAP_KEY = "FAT";
	public static final String CALORIES_MAP_KEY = "ENERC_KCAL";

	private Float totalWeight;
	private Map<String, Nutrient> totalNutrients;

}
