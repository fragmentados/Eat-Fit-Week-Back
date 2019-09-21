package com.eliasfb.efw.service;

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

public interface MenuService {
	ResponseDto create(CreateMenuDto menu);

	Menu delete(int id);

	ResponseDto clearMenu(int id, String startDate, String endDate);

	ShoppingListDto getShoppingList(int menuId, String startDate, String endDate);

	MenuDto findUserMenu(int userId, String startDate);

	Menu findById(int id);

	ResponseDto addDishToMenu(int menuId, AddDishToMenuDto dto);

	DishDto machineLearningSuggestDish(int menuId, MlSuggestDishDto dto);

	MenuDto updateDishDateOnMenu(int menuId, UpdateDishOnMenuDto dto);

	ResponseDto removeDishFromMenu(int menuId, AddDishToMenuDto dto);

	ResponseDto generateRandomMenu(Integer menuId, Integer userId, String startDate, String endDate);

	ResponseDto generateValidMenu(Integer menuId, Integer userId, String startDate, String endDate);

	MenuSpotFoundDto addDishToFirstValidSpotOnMenu(int userId, AddDishToMenuDto dto);

	MenuDto fillMenuFromTemplate(int menuId, FillMenuFromTemplateDto dto);

}
