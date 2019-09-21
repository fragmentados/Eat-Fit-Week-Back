package com.eliasfb.efw.dto.menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.eliasfb.efw.dto.stat.StatDto;
import com.eliasfb.efw.enums.NutritionalStatEnum;

import lombok.Data;

@Data
public class MenuDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String startDate;
	private List<MenuDayDto> days = new ArrayList<>();

	public List<StatDto> getStats() {
		// We obtain all the stats of the menus days
		List<StatDto> statList = this.days.stream().flatMap(d -> d.getStats().stream()).collect(Collectors.toList());
		// We group by those stats to obtain the summed value of each stat
		Map<String, Integer> stats = statList.stream().collect(
				Collectors.groupingBy(StatDto::getName, Collectors.summingInt(s -> Integer.parseInt(s.getValue()))));
		// We return them in the form of a list sorted by the custom order on the stat
		return stats.entrySet().stream().map(s -> new StatDto(s.getKey(), String.valueOf(s.getValue())))
				.sorted((s1, s2) -> NutritionalStatEnum.findByName(s1.getName()).getOrder()
						.compareTo(NutritionalStatEnum.findByName(s2.getName()).getOrder()))
				.collect(Collectors.toList());
	}

}
