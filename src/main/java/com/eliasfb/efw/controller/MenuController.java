package com.eliasfb.efw.controller;

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

import com.eliasfb.efw.dto.AddDishToMenuDto;
import com.eliasfb.efw.dto.CreateMenuDto;
import com.eliasfb.efw.dto.DishDto;
import com.eliasfb.efw.dto.FillMenuFromTemplateDto;
import com.eliasfb.efw.dto.MenuSpotFoundDto;
import com.eliasfb.efw.dto.MlSuggestDishDto;
import com.eliasfb.efw.dto.ResponseDto;
import com.eliasfb.efw.dto.UpdateDishOnMenuDto;
import com.eliasfb.efw.dto.menu.MenuDto;
import com.eliasfb.efw.dto.menu.ShoppingListDto;
import com.eliasfb.efw.model.Menu;
import com.eliasfb.efw.service.MenuService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping({ "/menus" })
public class MenuController {
	@Autowired
	private MenuService service;

	@PostMapping
	public ResponseDto create(@RequestBody CreateMenuDto dto) {
		return service.create(dto);
	}

	@GetMapping(path = { "/{id}" })
	public Menu findOne(@PathVariable("id") int id) {
		return service.findById(id);
	}

	@GetMapping(path = { "/{id}/shoppingList" })
	public ShoppingListDto getShoppingList(@PathVariable("id") int menuId,
			@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate) {
		return service.getShoppingList(menuId, startDate, endDate);
	}

	@GetMapping
	public MenuDto findUserMenu(@RequestParam("userId") int userId, @RequestParam("startDate") String startDate) {
		return service.findUserMenu(userId, startDate);
	}

	@PostMapping(path = { "/dish/firstValidSpot" })
	public MenuSpotFoundDto addDishToFirstValidSpot(@RequestParam("userId") Integer userId,
			@RequestBody AddDishToMenuDto dto) {
		return service.addDishToFirstValidSpotOnMenu(userId, dto);
	}

	@PostMapping(path = { "/{id}/dish" })
	public ResponseDto addDishToMenu(@PathVariable("id") int id, @RequestBody AddDishToMenuDto dto) {
		return service.addDishToMenu(id, dto);
	}

	@PostMapping(path = { "/{id}/suggest" })
	public DishDto machineLearningSuggestDish(@PathVariable("id") int id, @RequestBody MlSuggestDishDto dto) {
		return service.machineLearningSuggestDish(id, dto);
	}

	@PostMapping(path = { "/{id}/random" })
	public ResponseDto randomGenerate(@PathVariable("id") int menuId, @RequestParam("userId") Integer userId,
			@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate) {
		return service.generateRandomMenu(menuId, userId, startDate, endDate);
	}

	@PostMapping(path = { "/{id}/valid" })
	public ResponseDto generateValidMenu(@PathVariable("id") int menuId, @RequestParam("userId") Integer userId,
			@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate) {
		return service.generateValidMenu(menuId, userId, startDate, endDate);
	}

	@PutMapping(path = { "/{id}/dish" })
	public MenuDto updateDishDateOnMenu(@PathVariable("id") int id, @RequestBody UpdateDishOnMenuDto dto) {
		return service.updateDishDateOnMenu(id, dto);
	}

	@PutMapping(path = { "/{id}/template" })
	public MenuDto fillMenuFromTemplate(@PathVariable("id") int id, @RequestBody FillMenuFromTemplateDto dto) {
		return service.fillMenuFromTemplate(id, dto);
	}

	@DeleteMapping(path = { "/{id}/dish" })
	public ResponseDto removeDishFromMenu(@PathVariable("id") int id, @RequestBody AddDishToMenuDto dto) {
		return service.removeDishFromMenu(id, dto);
	}

	@DeleteMapping(path = { "/{id}/clearMenu" })
	public ResponseDto clearMenu(@PathVariable("id") int id,
			@RequestParam(value = "startDate", required = false) String startDate,
			@RequestParam(value = "endDate", required = false) String endDate) {
		return service.clearMenu(id, startDate, endDate);
	}

	@DeleteMapping(path = { "/{id}" })
	public Menu delete(@PathVariable("id") int id) {
		return service.delete(id);
	}

}
