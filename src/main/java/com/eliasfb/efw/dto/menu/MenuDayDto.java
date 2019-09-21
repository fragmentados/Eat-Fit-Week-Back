package com.eliasfb.efw.dto.menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.eliasfb.efw.dto.stat.StatDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MenuDayDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String date;
	private List<MenuDishDto> meals = new ArrayList<>();
	private List<StatDto> stats = new ArrayList<>();
}
