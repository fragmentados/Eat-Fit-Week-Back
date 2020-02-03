package com.eliasfb.efw.controller;

import java.util.List;
import java.util.Map;

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

import com.eliasfb.efw.dto.CreateOrUpdateIngredientDto;
import com.eliasfb.efw.dto.IngredientDto;
import com.eliasfb.efw.dto.NutritionEstimateDto;
import com.eliasfb.efw.dto.ResponseDto;
import com.eliasfb.efw.model.FoodCategory;
import com.eliasfb.efw.service.IngredientService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping({ "/ingredients" })
public class IngredientController {

	@Autowired
	private IngredientService service;

	@PostMapping
	public ResponseDto create(@RequestBody CreateOrUpdateIngredientDto createIngredient) {
		return service.create(createIngredient);
	}

	@GetMapping(path = { "/{id}" })
	public IngredientDto findOne(@PathVariable("id") int id) {
		return service.findById(id);
	}

	@GetMapping(path = { "/nutrition-estimate" })
	public NutritionEstimateDto getNutritionEstimate(@RequestParam("ingr") String ingrName) {
		return service.getNutritionEstimate(ingrName);
	}

	@GetMapping(path = { "/categories" })
	public List<FoodCategory> getFoodCategories() {
		return service.getFoodCategories();
	}

	@PutMapping(path = { "/{id}" })
	public ResponseDto update(@PathVariable("id") int id, @RequestBody CreateOrUpdateIngredientDto dto) {
		return service.update(id, dto);
	}

	@DeleteMapping(path = { "/{id}" })
	public ResponseDto delete(@PathVariable("id") int id) {
		return service.delete(id);
	}

	@GetMapping
	public List<IngredientDto> findUserIngredients(@RequestParam(required = false) Integer userId, @RequestParam(required = false) Boolean sortedByName) {
		return service.findUserIngredients(userId, sortedByName);
	}

	@GetMapping(path = "/map")
	public Map<String, List<IngredientDto>> findUserIngredientMap(@RequestParam(required = false) Integer userId) {
		return service.findUserIngredientMap(userId);
	}
}
