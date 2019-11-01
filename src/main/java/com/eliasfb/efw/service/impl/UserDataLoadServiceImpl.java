package com.eliasfb.efw.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eliasfb.efw.model.DisMealRel;
import com.eliasfb.efw.model.DisMealRelId;
import com.eliasfb.efw.model.Dish;
import com.eliasfb.efw.model.FoodCategory;
import com.eliasfb.efw.model.IngDisRel;
import com.eliasfb.efw.model.IngDisRelId;
import com.eliasfb.efw.model.Ingredient;
import com.eliasfb.efw.model.Meal;
import com.eliasfb.efw.model.User;
import com.eliasfb.efw.repository.FoodCategoryRepository;
import com.eliasfb.efw.service.UserDataLoadService;

@Service
public class UserDataLoadServiceImpl implements UserDataLoadService {

	@Autowired
	FoodCategoryRepository foodCategoryRepo;

	private List<Meal> getDefaultMeals(User user) {
		List<Meal> result = new ArrayList<>();
		result.add(new Meal("Desayuno", "00", user));
		result.add(new Meal("Comida", "01", user));
		result.add(new Meal("Cena", "02", user));
		return result;
	}

	private Set<Ingredient> getDefaultIngredients(User user) {
		Set<Ingredient> ingredients = new HashSet<>();
		FoodCategory tuber = this.foodCategoryRepo.findByName("Tubérculos");
		FoodCategory cereals = this.foodCategoryRepo.findByName("Cereales y derivados");
		FoodCategory fruits = this.foodCategoryRepo.findByName("Frutas y verduras");
		FoodCategory vegetables = this.foodCategoryRepo.findByName("Legumbres");
		FoodCategory meat = this.foodCategoryRepo.findByName("Carne, pescado y huevo");
		FoodCategory milk = this.foodCategoryRepo.findByName("Leche y productos lácteos");
		// double calories, double proteins, double fats, double carbohydrates
		// fruits
		ingredients.add(new Ingredient("Naranjas", 42, 0.8, 0.12, 8.6, fruits, user));
		ingredients.add(new Ingredient("Manzanas", 52, 0.3, 0.2, 14, fruits, user));
		ingredients.add(new Ingredient("Peras", 49, 0.4, 0, 10.6, fruits, user));
		ingredients.add(new Ingredient("Plátanos", 94, 1.2, 0.3, 20, fruits, user));
		ingredients.add(new Ingredient("Fresas", 40, 0.7, 0.5, 7, fruits, user));
		// cereals
		ingredients.add(new Ingredient("Pan", 210, 7.5, 1.3, 52, cereals, user));
		ingredients.add(new Ingredient("Choco Krispies", 385, 5.7, 2.5, 84, cereals, user));
		// vegetables
		ingredients.add(new Ingredient("Lentejas", 351, 23.8, 1.8, 54, vegetables, user));
		ingredients.add(new Ingredient("Garbanzos", 373, 19.4, 5, 55, vegetables, user));
		ingredients.add(new Ingredient("Habas", 65, 4.6, 0.4, 8.6, vegetables, user));
		// tuber
		ingredients.add(new Ingredient("Patatas", 80, 2.5, 0.1, 19, tuber, user));
		ingredients.add(new Ingredient("Zanahorias", 35, 1, 0, 7, tuber, user));
		// meat, fish and eggs
		ingredients.add(new Ingredient("Huevos", 150, 12.5, 11.1, 0, meat, user));
		ingredients.add(new Ingredient("Ternera", 131, 20.7, 5.4, 0, meat, user));
		ingredients.add(new Ingredient("Pollo", 197, 29.8, 7.8, 0, meat, user));
		ingredients.add(new Ingredient("Cerdo", 273, 16.6, 23, 0, meat, user));
		// milk and dery
		ingredients.add(new Ingredient("Leche", 65.4, 3.1, 3.8, 4.7, milk, user));
		ingredients.add(new Ingredient("Yogur", 97, 3.47, 5, 3.98, milk, user));
		ingredients.add(new Ingredient("Queso", 350, 23, 28, 2, milk, user));

		return ingredients;
	}

	private Set<Dish> getDefaultDishes(User user) {
		Set<Dish> dishes = new HashSet<>();
		Set<Ingredient> ingredients = user.getIngredients();
		// Milk with Cereals
		Ingredient milk = this.findIngredient(ingredients, "Leche");
		Ingredient cereals = this.findIngredient(ingredients, "Choco Krispies");
		if (milk != null && cereals != null) {
			Dish milkCereals = new Dish();
			milkCereals.setName("Leche con cereales");
			milkCereals.setRecipe(
					"Verter la leche en el bol y rellenar con los cereales. Repetir a gusto del consumidor!");
			IngDisRel milkDish = new IngDisRel(new IngDisRelId(milkCereals, milk), 250D);
			IngDisRel cerealDish = new IngDisRel(new IngDisRelId(milkCereals, cereals), 60D);
			milkCereals.setIngredients(Arrays.asList(milkDish, cerealDish));
			milkCereals.setMeals(user.getMeals().stream().map(m -> new DisMealRel(new DisMealRelId(milkCereals, m)))
					.collect(Collectors.toList()));
			dishes.add(milkCereals);
		}
		return dishes;
	}

	private Ingredient findIngredient(Set<Ingredient> ingredients, String name) {
		return ingredients.stream().filter(i -> name.equals(i.getName())).findAny().orElse(null);
	}

	@Override
	public User loadDefaultData(User user) {
		user.setIngredients(getDefaultIngredients(user));
		// TODO Pending to review the default dishes implementation -> Error duplicate
		// Entity on Hibernate
		// user.setDishes(getDefaultDishes(user));
		return user;
	}

	@Override
	public User loadDefaultMeals(User user) {
		user.setMeals(getDefaultMeals(user));
		return user;
	}

}
