package com.eliasfb.efw.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MachineLearningInstance implements Serializable {
	private String weekDay;
	private String mealName;
	private String dishName;

	public MachineLearningInstance(String weekDay, String mealName) {
		super();
		this.weekDay = weekDay;
		this.mealName = mealName;
	}
}
