package com.eliasfb.efw.dto.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.eliasfb.efw.dto.MenuTemplateDto;
import com.eliasfb.efw.dto.menu.MenuDayDto;
import com.eliasfb.efw.dto.menu.MenuDishDto;
import com.eliasfb.efw.dto.menu.MenuDto;
import com.eliasfb.efw.dto.stat.StatDto;
import com.eliasfb.efw.enums.NutritionalStatEnum;
import com.eliasfb.efw.model.Dish;
import com.eliasfb.efw.model.Menu;
import com.eliasfb.efw.model.MenuDisRel;
import com.eliasfb.efw.model.MenuTemplate;

@Mapper(componentModel = "spring", uses = DishToDtoMapper.class)
public abstract class MenuToDtoMapper {

	@Autowired
	DishToDtoMapper dishMapper;

	public abstract List<MenuDto> menuListToMenuDtoList(List<Menu> menu);

	@Mapping(source = "dishes", target = "days")
	public abstract MenuDto menuToMenuDto(Menu menu);

	public List<MenuDayDto> menuDisSetToDto(Set<MenuDisRel> menuDisRels) {
		List<MenuDayDto> menuDays = new ArrayList<>();
		Map<String, List<MenuDishDto>> menuDisByDate = groupMenuDisByDate(menuDisRels);
		menuDisByDate.keySet().forEach(date -> menuDays
				.add(new MenuDayDto(date, menuDisByDate.get(date), getDayStatList(menuDisByDate.get(date)))));
		// We sort the days by date
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return menuDays.stream().sorted((d1, d2) -> LocalDate.parse(d1.getDate(), dateFormatter)
				.compareTo(LocalDate.parse(d2.getDate(), dateFormatter))).collect(Collectors.toList());
	}

	private Map<String, List<MenuDishDto>> groupMenuDisByDate(Set<MenuDisRel> menuDisRels) {
		Map<String, List<MenuDishDto>> menuDisByDate = new HashMap<>();
		menuDisRels.forEach(menuDis -> {
			// Format date without hours
			DateTimeFormatter dateHourFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String dishDate = LocalDate.parse(menuDis.getId().getDishDate(), dateHourFormatter).format(dateFormatter);
			if (menuDisByDate.containsKey(dishDate)) {
				menuDisByDate.get(dishDate).add(menuDisToDto(menuDis));
			} else {
				List<MenuDishDto> dishes = new ArrayList<>();
				dishes.add(menuDisToDto(menuDis));
				menuDisByDate.put(dishDate, dishes);
			}
		});
		return menuDisByDate;
	}

	private List<StatDto> getDayStatList(List<MenuDishDto> menuDtos) {
		List<StatDto> menuStats = new ArrayList<>();
		Integer calories = menuDtos.stream().collect(Collectors.summingInt(d -> d.getCalories()));
		menuStats.add(new StatDto(NutritionalStatEnum.CALORIES.getName(), String.valueOf(calories)));
		Integer proteins = menuDtos.stream().collect(Collectors.summingInt(d -> d.getProteins()));
		menuStats.add(new StatDto(NutritionalStatEnum.PROTEINS.getName(), String.valueOf(proteins)));
		Integer fats = menuDtos.stream().collect(Collectors.summingInt(d -> d.getFats()));
		menuStats.add(new StatDto(NutritionalStatEnum.FATS.getName(), String.valueOf(fats)));
		Integer carbohydrates = menuDtos.stream().collect(Collectors.summingInt(d -> d.getCarbohydrates()));
		menuStats.add(new StatDto(NutritionalStatEnum.CARBOHYDRATES.getName(), String.valueOf(carbohydrates)));
		return menuStats;
	}

	public MenuDishDto menuDisToDto(MenuDisRel menuDisRel) {
		Dish dish = menuDisRel.getId().getDish();
		MenuDishDto menuDishDto = dishMapper.dishToMenuDishDto(dish);
		menuDishDto.setDate(menuDisRel.getId().getDishDate());
		return menuDishDto;
	}

	public abstract List<MenuTemplateDto> menuTemplateListToMenuTemplateDtoList(List<MenuTemplate> menuTemplates);

	public abstract MenuTemplateDto menuTemplateToMenuTemplateDto(MenuTemplate menuTemplate);

}
