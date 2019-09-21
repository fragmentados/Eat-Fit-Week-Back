package com.eliasfb.efw.service.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.eliasfb.efw.dto.AddDishToMenuDto;
import com.eliasfb.efw.dto.CreateMenuDto;
import com.eliasfb.efw.dto.ResponseDto;
import com.eliasfb.efw.dto.mapper.MenuToDtoMapper;
import com.eliasfb.efw.dto.menu.MenuDto;
import com.eliasfb.efw.model.Dish;
import com.eliasfb.efw.model.Menu;
import com.eliasfb.efw.model.MenuDisRel;
import com.eliasfb.efw.model.MenuDisRelId;
import com.eliasfb.efw.repository.DishRepository;
import com.eliasfb.efw.repository.MenuDisRelRepository;
import com.eliasfb.efw.repository.MenuRepository;

@RunWith(MockitoJUnitRunner.class)
public class MenuServiceImplTest {

	@Mock
	private DishRepository dishRepository;

	@Mock
	private MenuDisRelRepository menuDisRelRepository;

	@Mock
	MenuToDtoMapper mapper;

	@Mock
	private MenuRepository repository;

	@InjectMocks
	MenuServiceImpl service;

	@Test
	public void createTest() {
		Mockito.when(this.repository.save(Matchers.any())).thenReturn(new Menu());
		CreateMenuDto dto = new CreateMenuDto(1, "2019-01-01");
		ResponseDto response = service.create(dto);
		assertNotNull(response);
		assertThat(response.getErrorCode(), equalTo(ResponseDto.OK_CODE));
	}

	@Test
	public void createNullDtoTest() {
		Mockito.when(this.repository.save(Matchers.any())).thenReturn(new Menu());
		CreateMenuDto dto = null;
		ResponseDto response = service.create(dto);
		assertNotNull(response);
		assertThat(response.getErrorCode(), equalTo(ResponseDto.ERROR_CODE));
	}

	@Test
	public void deleteTest() {
		Integer menuId = 1;
		Menu menu = new Menu();
		Mockito.when(this.repository.findOne(menuId)).thenReturn(menu);
		Menu menuReturned = service.delete(menuId);
		assertNotNull(menuReturned);
		assertThat(menuReturned, equalTo(menu));
		Mockito.verify(this.repository, Mockito.times(1)).delete(menu);
	}

	@Test
	public void deleteMenuNotFoundTest() {
		Integer menuId = 1;
		Mockito.when(this.repository.findOne(menuId)).thenReturn(null);
		Menu menuReturned = service.delete(menuId);
		assertNull(menuReturned);
		Mockito.verify(this.repository, Mockito.times(0)).delete(Matchers.any());
	}

	@Test
	public void clearMenuNotFoundTest() {
		Integer menuId = 1;
		Mockito.when(this.repository.findOne(menuId)).thenReturn(null);
		ResponseDto response = this.service.clearMenu(menuId, null, null);
		assertNotNull(response);
		Mockito.verify(this.menuDisRelRepository, Mockito.times(0)).deleteByMenuIdAndDates(Matchers.any(),
				Matchers.any(), Matchers.any());
		Mockito.verify(this.menuDisRelRepository, Mockito.times(0)).deleteByMenuIdAndDate(Matchers.any(),
				Matchers.any());
		Mockito.verify(this.menuDisRelRepository, Mockito.times(0)).deleteByMenuId(Matchers.any());
	}

	@Test
	public void clearMenuEmptyMenuTest() {
		Integer menuId = 1;
		Menu menu = new Menu();
		menu.setDishes(new HashSet<>());
		Mockito.when(this.repository.findOne(menuId)).thenReturn(menu);
		ResponseDto response = this.service.clearMenu(menuId, null, null);
		assertNotNull(response);
		Mockito.verify(this.menuDisRelRepository, Mockito.times(0)).deleteByMenuIdAndDates(Matchers.any(),
				Matchers.any(), Matchers.any());
		Mockito.verify(this.menuDisRelRepository, Mockito.times(0)).deleteByMenuIdAndDate(Matchers.any(),
				Matchers.any());
		Mockito.verify(this.menuDisRelRepository, Mockito.times(0)).deleteByMenuId(Matchers.any());
	}

	@Test
	public void clearMenuWithoutDateRangeTest() {
		Integer menuId = 1;
		Menu menu = new Menu();
		MenuDisRel mdr = new MenuDisRel();
		menu.setDishes(new HashSet<>(Arrays.asList(mdr)));
		Mockito.when(this.repository.findOne(menuId)).thenReturn(menu);
		ResponseDto response = this.service.clearMenu(menuId, null, null);
		assertNotNull(response);
		Mockito.verify(this.menuDisRelRepository, Mockito.times(0)).deleteByMenuIdAndDates(Matchers.any(),
				Matchers.any(), Matchers.any());
		Mockito.verify(this.menuDisRelRepository, Mockito.times(0)).deleteByMenuIdAndDate(Matchers.any(),
				Matchers.any());
		Mockito.verify(this.menuDisRelRepository, Mockito.times(1)).deleteByMenuId(menuId);
	}

	@Test
	public void clearMenuWithStartDateTest() {
		Integer menuId = 1;
		String startDate = "2019-09-01";
		Menu menu = new Menu();
		MenuDisRel mdr = new MenuDisRel();
		menu.setDishes(new HashSet<>(Arrays.asList(mdr)));
		Mockito.when(this.repository.findOne(menuId)).thenReturn(menu);
		ResponseDto response = this.service.clearMenu(menuId, startDate, null);
		assertNotNull(response);
		Mockito.verify(this.menuDisRelRepository, Mockito.times(0)).deleteByMenuIdAndDates(Matchers.any(),
				Matchers.any(), Matchers.any());
		Mockito.verify(this.menuDisRelRepository, Mockito.times(1)).deleteByMenuIdAndDate(menuId, startDate);
		Mockito.verify(this.menuDisRelRepository, Mockito.times(0)).deleteByMenuId(Matchers.any());
	}

	@Test
	public void clearMenuWithDateRangeTest() {
		Integer menuId = 1;
		String startDate = "2019-09-01";
		String endDate = "2019-09-02";
		Menu menu = new Menu();
		MenuDisRel mdr = new MenuDisRel();
		menu.setDishes(new HashSet<>(Arrays.asList(mdr)));
		Mockito.when(this.repository.findOne(menuId)).thenReturn(menu);
		ResponseDto response = this.service.clearMenu(menuId, startDate, endDate);
		assertNotNull(response);
		Mockito.verify(this.menuDisRelRepository, Mockito.times(1)).deleteByMenuIdAndDates(menuId, startDate, endDate);
		Mockito.verify(this.menuDisRelRepository, Mockito.times(0)).deleteByMenuIdAndDate(Matchers.any(),
				Matchers.any());
		Mockito.verify(this.menuDisRelRepository, Mockito.times(0)).deleteByMenuId(Matchers.any());
	}

	@Test
	public void findUserMenuTest() {
		Integer userId = 1;
		String startDate = "2019-09-01";
		Menu menu = new Menu();
		MenuDto dto = new MenuDto();
		Mockito.when(this.repository.findByUserIdAndStartDate(Matchers.anyInt(), Matchers.anyString()))
				.thenReturn(menu);
		Mockito.when(this.mapper.menuToMenuDto(menu)).thenReturn(dto);
		MenuDto dtoReturned = this.service.findUserMenu(userId, startDate);
		assertNotNull(dtoReturned);
		assertThat(dtoReturned, equalTo(dto));
	}

	@Test
	public void findUserMenuNotFoundTest() {
		Integer userId = 1;
		String startDate = "2019-09-01";
		Mockito.when(this.repository.findByUserIdAndStartDate(Matchers.anyInt(), Matchers.any())).thenReturn(null);
		MenuDto dtoReturned = this.service.findUserMenu(userId, startDate);
		assertNotNull(dtoReturned);
	}

	@Test
	public void addDishToMenuTest() {
		Integer menuId = 1;
		Menu menu = new Menu();
		menu.setDishes(new HashSet<>());
		Dish dish = new Dish();
		AddDishToMenuDto dto = new AddDishToMenuDto(1, "2019-09-01");
		Mockito.when(this.repository.findOne(menuId)).thenReturn(menu);
		Mockito.when(this.dishRepository.findOne(dto.getDishId())).thenReturn(dish);
		ResponseDto response = this.service.addDishToMenu(menuId, dto);
		assertNotNull(response);
		assertThat(response.getErrorCode(), equalTo(ResponseDto.OK_CODE));
		Mockito.verify(this.repository, Mockito.times(1)).save(Mockito.any());
	}

	@Test
	public void addDishToMenuAlreadyContainedOnMenuTest() {
		Integer menuId = 1;
		AddDishToMenuDto dto = new AddDishToMenuDto(1, "2019-09-01");
		Menu menu = new Menu();
		Dish dish = new Dish();
		MenuDisRel mdr = new MenuDisRel(new MenuDisRelId(menu, dish, dto.getDate()));
		menu.setDishes(new HashSet<>(Arrays.asList(mdr)));
		Mockito.when(this.repository.findOne(menuId)).thenReturn(menu);
		Mockito.when(this.dishRepository.findOne(dto.getDishId())).thenReturn(dish);
		ResponseDto response = this.service.addDishToMenu(menuId, dto);
		assertNotNull(response);
		assertThat(response.getErrorCode(), equalTo(ResponseDto.OK_CODE));
		Mockito.verify(this.repository, Mockito.times(0)).save(Mockito.any());
	}

}
