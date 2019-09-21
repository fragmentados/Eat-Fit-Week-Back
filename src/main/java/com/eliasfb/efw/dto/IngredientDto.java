package com.eliasfb.efw.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.eliasfb.efw.dto.stat.StatDto;

import lombok.Data;

@Data
public class IngredientDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private FoodCategoryDto category;
	private List<StatDto> stats = new ArrayList<>();

}
