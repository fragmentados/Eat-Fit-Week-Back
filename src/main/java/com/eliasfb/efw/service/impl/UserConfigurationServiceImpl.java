package com.eliasfb.efw.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eliasfb.efw.model.UserConfiguration;
import com.eliasfb.efw.repository.UserConfigurationRepository;
import com.eliasfb.efw.service.UserConfigurationService;

@Service
public class UserConfigurationServiceImpl implements UserConfigurationService {

	@Autowired
	UserConfigurationRepository repository;

	@Override
	public <T> T findUserConfigurationByNameOrDefault(Integer userId, String name, Class<T> type, T defaultValue) {
		UserConfiguration conf = repository.findByNameAndUser(name, userId);
		return (conf != null && !conf.getValue().isEmpty()) ? this.parseClass(conf.getValue(), type) : defaultValue;
	}

	@Override
	public <T> List<T> findUserConfigurationListByNameOrDefault(Integer userId, String name, Class<T> type,
			List<T> defaultValue) {
		List<T> result = defaultValue;
		UserConfiguration conf = repository.findByNameAndUser(name, userId);
		if (conf != null && !conf.getValue().isEmpty()) {
			String[] confValuesSplitted = conf.getValue().split(", ");
			result = Stream.of(confValuesSplitted).map(c -> parseClass(c, type)).collect(Collectors.toList());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public <T> T parseClass(String confValue, Class<T> type) {
		if (type.equals(Integer.class)) {
			return (T) Integer.valueOf(confValue);
		} else if (type.equals(Double.class)) {
			return (T) Double.valueOf(confValue);
		}
		// Fallback to String
		return (T) confValue;
	}

}
