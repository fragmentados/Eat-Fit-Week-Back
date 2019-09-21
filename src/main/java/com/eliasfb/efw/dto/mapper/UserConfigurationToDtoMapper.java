package com.eliasfb.efw.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;

import com.eliasfb.efw.dto.UserConfigurationsDto;
import com.eliasfb.efw.enums.UserConfigurationEnum;
import com.eliasfb.efw.model.User;
import com.eliasfb.efw.model.UserConfiguration;
import com.eliasfb.efw.model.UserConfigurationId;

@Mapper(componentModel = "spring")
public abstract class UserConfigurationToDtoMapper {

	public List<UserConfiguration> toEntity(UserConfigurationsDto dto, Integer userId) {
		List<UserConfiguration> confs = new ArrayList<>();
		confs.add(createUserConfiguration(userId, UserConfigurationEnum.MAX_CALORIES_PER_WEEK.getName(),
				dto.getMaxCaloriesPerWeek().toString()));
		confs.add(createUserConfiguration(userId, UserConfigurationEnum.MAX_FATS_PER_WEEK.getName(),
				dto.getMaxFatsPerWeek().toString()));
		confs.add(createUserConfiguration(userId, UserConfigurationEnum.MAX_PROTEINS_PER_WEEK.getName(),
				dto.getMaxProteinsPerWeek().toString()));
		confs.add(createUserConfiguration(userId, UserConfigurationEnum.MAX_CARBOHYDRATES_PER_WEEK.getName(),
				dto.getMaxCarbohydratesPerWeek().toString()));
		confs.add(createUserConfiguration(userId, UserConfigurationEnum.BANNED_CATEGORIES.getName(),
				joinBannedCategories(dto)));
		return confs;
	}

	private String joinBannedCategories(UserConfigurationsDto dto) {
		return dto.getBannedCategories().stream().map(bc -> bc.getId().toString()).collect(Collectors.joining(", "));
	}

	private UserConfiguration createUserConfiguration(Integer userId, String confName, String value) {
		UserConfiguration conf = new UserConfiguration();
		UserConfigurationId id = new UserConfigurationId(confName, new User(userId));
		conf.setId(id);
		conf.setValue(value);
		return conf;
	}
}
