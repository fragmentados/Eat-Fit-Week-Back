package com.eliasfb.efw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eliasfb.efw.dto.CreateDishDto;
import com.eliasfb.efw.dto.DishDto;
import com.eliasfb.efw.dto.ResponseDto;
import com.eliasfb.efw.model.Dish;
import com.eliasfb.efw.model.Ingredient;
import com.eliasfb.efw.service.DishService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping({ "/dishes" })
public class DishController {
	@Autowired
	private DishService service;

	@PostMapping
	public ResponseDto create(@RequestBody CreateDishDto dish) {
		return this.service.create(dish);
	}

	@GetMapping(path = { "/{id}" })
	public DishDto findOne(@PathVariable("id") int id) {
		return this.service.findById(id);
	}

	@PutMapping(path = { "/{id}" })
	public ResponseDto update(@PathVariable("id") int id, @RequestBody CreateDishDto dto) {
		return this.service.update(id, dto);
	}

	@PutMapping(path = { "/{id}/ingredient" })
	public Dish addIngredientToDish(@PathVariable("id") int id, @RequestBody Ingredient ingredient) {
		return this.service.addIngredientToDish(id, ingredient);
	}

	@DeleteMapping(path = { "/{id}" })
	public ResponseDto delete(@PathVariable("id") int id) {
		return this.service.delete(id);
	}

	@GetMapping
	public List<DishDto> findUserDishes(@RequestParam(required = false) Integer userId) {
		return this.service.findUserDishes(userId);
	}
}
