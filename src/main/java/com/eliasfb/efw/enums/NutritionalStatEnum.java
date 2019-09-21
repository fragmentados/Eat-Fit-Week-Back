package com.eliasfb.efw.enums;

public enum NutritionalStatEnum {

	//@formatter:off
	CALORIES("Calories", 1), 
	PROTEINS("Proteins", 2), 
	FATS("Fats", 3), 
	CARBOHYDRATES("Carbohydrates", 4);
	//@formatter:on

	private String name;
	private Integer order;

	private NutritionalStatEnum(String name, Integer order) {
		this.name = name;
		this.order = order;
	}

	public String getName() {
		return name;
	}

	public Integer getOrder() {
		return order;
	}

	public static NutritionalStatEnum findByName(String name) {
		for (NutritionalStatEnum n : values()) {
			if (n.getName().equals(name)) {
				return n;
			}
		}
		return null;
	}

}
