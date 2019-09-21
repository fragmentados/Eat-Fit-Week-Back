package com.eliasfb.efw.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.eliasfb.efw.dto.stat.StatDto;

import lombok.Data;

@Data
public class DishDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private String recipe;
	private List<DishIngredientDto> ingredients;
	private List<MealDto> meals;
	private List<StatDto> stats = new ArrayList<>();
}
