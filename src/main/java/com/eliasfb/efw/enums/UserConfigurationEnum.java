package com.eliasfb.efw.enums;

public enum UserConfigurationEnum {
	//@formatter:off
	MAX_CALORIES_PER_WEEK("maxCaloriesPerWeek"),
	MAX_PROTEINS_PER_WEEK("maxProteinsPerWeek"),
	MAX_FATS_PER_WEEK("maxFatsPerWeek"),
	MAX_CARBOHYDRATES_PER_WEEK("maxCarbohydratesPerWeek"),
	BANNED_CATEGORIES("bannedCategories");	
	//@formatter:on

	private String name;

	private UserConfigurationEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
