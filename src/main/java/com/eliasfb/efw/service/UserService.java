package com.eliasfb.efw.service;

import java.util.List;

import com.eliasfb.efw.dto.LoginDto;
import com.eliasfb.efw.dto.MealDto;
import com.eliasfb.efw.dto.ResponseDto;
import com.eliasfb.efw.dto.UserConfigurationsDto;
import com.eliasfb.efw.model.Meal;
import com.eliasfb.efw.model.User;

public interface UserService {

	User create(User user, Boolean defaultData);

	User delete(int id);

	List<User> findAll();

	User findById(int id);

	ResponseDto updateUserConfigurations(int id, UserConfigurationsDto userConfs);

	UserConfigurationsDto findConfigurations(int id);

	User update(User user);

	ResponseDto login(LoginDto login);

	List<MealDto> findUserMeals(Integer userId);

	Meal deleteMeal(int id);
}
