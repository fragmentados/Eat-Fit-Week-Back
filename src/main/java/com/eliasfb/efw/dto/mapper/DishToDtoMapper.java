package com.eliasfb.efw.dto.mapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.eliasfb.efw.dto.CreateDishDto;
import com.eliasfb.efw.dto.DishDto;
import com.eliasfb.efw.dto.DishIngredientDto;
import com.eliasfb.efw.dto.MealDto;
import com.eliasfb.efw.dto.menu.MenuDishDto;
import com.eliasfb.efw.dto.stat.StatDto;
import com.eliasfb.efw.enums.NutritionalStatEnum;
import com.eliasfb.efw.model.DisMealRel;
import com.eliasfb.efw.model.DisMealRelId;
import com.eliasfb.efw.model.Dish;
import com.eliasfb.efw.model.IngDisRel;
import com.eliasfb.efw.model.IngDisRelId;
import com.eliasfb.efw.model.Ingredient;
import com.eliasfb.efw.model.Meal;
import com.eliasfb.efw.model.User;

@Mapper(componentModel = "spring")
public abstract class DishToDtoMapper {

	@Autowired
	private IngredientToIngredientDtoMapper ingredientMapper;

	public List<DishDto> dishListToDishDtoList(List<Dish> dishes) {
		return dishes.stream().map(di -> toDto(di)).collect(Collectors.toList());
	}

	@Mapping(source = "ingredients", target = "ingredients", ignore = true)
	@Mapping(source = "meals", target = "meals", ignore = true)
	protected abstract DishDto dishToDishDto(Dish dish);

	public abstract MenuDishDto dishToMenuDishDto(Dish dish);

	public DishDto toDto(Dish dish) {
		DishDto dishDto = dishToDishDto(dish);
		dishDto.setIngredients(dish.getIngredients().stream()
				.map(i -> new DishIngredientDto(ingredientMapper.toDto(i.getId().getIngredient()), i.getQuantity()))
				.collect(Collectors.toList()));
		dishDto.setMeals(dish.getMeals().stream()
				.map(m -> new MealDto(m.getId().getMeal().getId(), m.getId().getMeal().getName()))
				.collect(Collectors.toList()));
		dishDto.setStats(getDishStats(dish));
		return dishDto;
	}

	List<DishDto> dishesToDishesDto(List<Dish> dishes) {
		return dishes.stream().map(d -> toDto(d)).collect(Collectors.toList());
	}

	@Mapping(source = "ingredients", target = "ingredients", ignore = true)
	@Mapping(source = "meals", target = "meals", ignore = true)
	protected abstract Dish createDishDtoToDish(CreateDishDto createDish);

	public Dish toEntity(CreateDishDto dto) {
		Dish dish = createDishDtoToDish(dto);
		if (dto.getUserId() != null) {
			User user = new User();
			user.setId(dto.getUserId());
			dish.setUsers(new HashSet<>(Arrays.asList(user)));
		}
		dish.setIngredients(dto.getIngredients().stream()
				.map(ig -> new IngDisRel(new IngDisRelId(dish, new Ingredient(ig.getId())), ig.getQuantity()))
				.collect(Collectors.toList()));
		dish.setMeals(dto.getMeals().stream().map(meal -> new DisMealRel(new DisMealRelId(dish, new Meal(meal))))
				.collect(Collectors.toList()));
		return dish;
	}

	public List<StatDto> getDishStats(Dish dish) {
		List<StatDto> stats = new ArrayList<>();
		stats.add(new StatDto(NutritionalStatEnum.CALORIES.getName(),
				String.valueOf(Math.round(dish.getCalories() * 100.0) / 100.0)));
		stats.add(new StatDto(NutritionalStatEnum.FATS.getName(),
				String.valueOf(Math.round(dish.getFats() * 100.0) / 100.0)));
		stats.add(new StatDto(NutritionalStatEnum.PROTEINS.getName(),
				String.valueOf(Math.round(dish.getProteins() * 100.0) / 100.0)));
		stats.add(new StatDto(NutritionalStatEnum.CARBOHYDRATES.getName(),
				String.valueOf(Math.round(dish.getCarbohydrates() * 100.0) / 100.0)));
		return stats;
	}

}
