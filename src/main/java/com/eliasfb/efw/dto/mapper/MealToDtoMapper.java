package com.eliasfb.efw.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.eliasfb.efw.dto.MealDto;
import com.eliasfb.efw.model.Meal;

@Mapper(componentModel = "spring")
public abstract class MealToDtoMapper {

	public abstract Meal mealDtoToMeal(MealDto mealDto);

	public abstract List<Meal> mealDtoListToMealList(List<MealDto> mealDto);

	public abstract MealDto mealToDto(Meal meal);

	public abstract List<MealDto> mealListToMealDtoList(List<Meal> meal);
}
