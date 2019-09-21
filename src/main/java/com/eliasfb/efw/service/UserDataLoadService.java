package com.eliasfb.efw.service;

import java.util.List;

import com.eliasfb.efw.model.Meal;
import com.eliasfb.efw.model.User;

public interface UserDataLoadService {
	List<Meal> getDefaultMeals(User user);
}
