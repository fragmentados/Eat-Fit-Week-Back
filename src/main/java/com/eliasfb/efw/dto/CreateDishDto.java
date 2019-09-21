package com.eliasfb.efw.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class CreateDishDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer userId;
	private String name;
	private String recipe;
	private List<CreateIngredientDishDto> ingredients;
	private List<Integer> meals;
}
