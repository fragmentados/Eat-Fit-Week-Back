package com.eliasfb.efw.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class CreateOrUpdateIngredientDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer userId;
	private String name;
	private Integer categoryId;
	private Double calories;
	private Double proteins;
	private Double fats;
	private Double carbohydrates;

}
