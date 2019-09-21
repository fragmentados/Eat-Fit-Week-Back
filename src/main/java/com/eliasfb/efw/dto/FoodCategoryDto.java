package com.eliasfb.efw.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class FoodCategoryDto implements Serializable {

	private Integer id;
	private String name;
}
