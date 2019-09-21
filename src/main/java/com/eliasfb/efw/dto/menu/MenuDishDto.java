package com.eliasfb.efw.dto.menu;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import com.eliasfb.efw.dto.stat.StatDto;
import com.eliasfb.efw.enums.NutritionalStatEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class MenuDishDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private String date;

	@JsonIgnore
	private Integer calories;
	@JsonIgnore
	private Integer proteins;
	@JsonIgnore
	private Integer fats;
	@JsonIgnore
	private Integer carbohydrates;

	public List<StatDto> getStats() {
		return Arrays.asList(new StatDto(NutritionalStatEnum.CALORIES.getName(), String.valueOf(this.calories)),
				new StatDto(NutritionalStatEnum.PROTEINS.getName(), String.valueOf(this.proteins)),
				new StatDto(NutritionalStatEnum.FATS.getName(), String.valueOf(this.fats)),
				new StatDto(NutritionalStatEnum.CARBOHYDRATES.getName(), String.valueOf(this.carbohydrates)));
	}
}
