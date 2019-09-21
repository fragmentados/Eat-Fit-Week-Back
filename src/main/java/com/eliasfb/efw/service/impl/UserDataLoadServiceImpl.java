package com.eliasfb.efw.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.eliasfb.efw.model.Meal;
import com.eliasfb.efw.model.User;
import com.eliasfb.efw.service.UserDataLoadService;

@Service
public class UserDataLoadServiceImpl implements UserDataLoadService {

	@Override
	public List<Meal> getDefaultMeals(User user) {
		List<Meal> result = new ArrayList<>();
		result.add(new Meal("Desayuno", "00", user));
		result.add(new Meal("Comida", "01", user));
		result.add(new Meal("Cena", "02", user));
		return result;
	}

}
