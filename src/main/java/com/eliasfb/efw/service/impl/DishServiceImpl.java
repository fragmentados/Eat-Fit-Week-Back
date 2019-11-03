package com.eliasfb.efw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eliasfb.efw.dto.CreateDishDto;
import com.eliasfb.efw.dto.DishDto;
import com.eliasfb.efw.dto.ResponseDto;
import com.eliasfb.efw.dto.mapper.DishToDtoMapper;
import com.eliasfb.efw.model.Dish;
import com.eliasfb.efw.model.IngDisRel;
import com.eliasfb.efw.model.IngDisRelId;
import com.eliasfb.efw.model.Ingredient;
import com.eliasfb.efw.repository.DishRepository;
import com.eliasfb.efw.service.DishService;

@Service
public class DishServiceImpl implements DishService {

	@Autowired
	private DishRepository repository;
	@Autowired
	private DishToDtoMapper mapper;

	@Override
	@Transactional
	public ResponseDto create(CreateDishDto createDish) {
		ResponseDto response = new ResponseDto(ResponseDto.OK_CODE, "Dish created successfully");
		List<Dish> dishWithSameName = this.repository.findByUserAndByName(createDish.getUserId(), createDish.getName());
		if (dishWithSameName.isEmpty()) {
			Dish dishToCreate = this.mapper.toEntity(createDish);
			this.repository.save(dishToCreate);
		} else {
			response = new ResponseDto(ResponseDto.UNIQUE_CONSTRAINT_CODE,
					"There already exists a dish with this name for this user");
		}
		return response;
	}

	@Override
	public ResponseDto delete(int id) {
		Dish dish = this.repository.findOne(id);
		if (dish != null) {
			this.repository.delete(dish);
		}
		return new ResponseDto(ResponseDto.OK_CODE, "Dish with name = '" + dish.getName() + "' deleted correctly.");
	}

	@Override
	public List<DishDto> findUserDishes(Integer userId) {
		List<Dish> dishes;
		if (userId == null) {
			dishes = this.repository.findAll();
		} else {
			dishes = this.repository.findUserDishes(userId);
		}
		return this.mapper.dishListToDishDtoList(dishes);
	}

	@Override
	public List<Dish> findAll() {
		return this.repository.findAll();
	}

	@Override
	public DishDto findById(int id) {
		return this.mapper.toDto(this.repository.findOne(id));
	}

	@Override
	@Transactional
	public ResponseDto update(Integer dishId, CreateDishDto dto) {
		ResponseDto response = new ResponseDto(ResponseDto.OK_CODE, "Dish updated successfully");
		Dish dish = this.repository.findOne(dishId);
		if (dish != null) {
			List<Dish> dishWithSameName = this.repository.findOtherDishByUserAndByName(dto.getUserId(), dto.getName(),
					dish.getId());
			if (dishWithSameName.isEmpty()) {
				// All fields should be updated except users
				Dish updateDish = this.mapper.toEntity(dto);
				// TODO EFB REVIEW HOW TO UPDATE MEALS
				// We delete the meals not found
				/*
				 * List<DisMealRel> mealsToUpdate = new ArrayList<>();
				 * mealsToUpdate.addAll(updateDish.getMeals());
				 * dish.getMeals().stream().filter(m ->
				 * !updateDish.getMeals().contains(m)).forEach(m -> {
				 * this.dishMealRepository.delete(m); mealsToUpdate.remove(m); });
				 * updateDish.setMeals(mealsToUpdate);
				 */
				updateDish.setUsers(dish.getUsers());
				updateDish.setId(dishId);
				this.repository.save(updateDish);
			} else {
				response = new ResponseDto(ResponseDto.UNIQUE_CONSTRAINT_CODE,
						"There is already a dish with this name for this user");
			}
		} else {
			response = new ResponseDto(ResponseDto.ERROR_CODE, "Dish not found");
		}
		return response;
	}

	@Override
	public Dish addIngredientToDish(int dishId, Ingredient ingredient) {
		Dish dish = this.repository.findOne(dishId);
		IngDisRelId id = new IngDisRelId(dish, ingredient);
		IngDisRel ingDisRel = new IngDisRel(id, 0d);
		if (!dish.getIngredients().contains(ingDisRel)) {
			dish.getIngredients().add(ingDisRel);
			this.repository.save(dish);
		}

		return dish;
	}

}
