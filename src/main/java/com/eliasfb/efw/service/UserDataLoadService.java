package com.eliasfb.efw.service;

import com.eliasfb.efw.model.User;

public interface UserDataLoadService {

	User loadDefaultMeals(User user);

	User loadDefaultData(User user);

}
