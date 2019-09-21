package com.eliasfb.efw.service;

import java.util.List;

public interface UserConfigurationService {

	<T> T findUserConfigurationByNameOrDefault(Integer userId, String name, Class<T> type, T defaultValue);

	<T> List<T> findUserConfigurationListByNameOrDefault(Integer userId, String name, Class<T> type,
			List<T> defaultValue);
}
