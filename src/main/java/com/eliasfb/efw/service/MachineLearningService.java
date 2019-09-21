package com.eliasfb.efw.service;

import java.util.List;

import com.eliasfb.efw.model.MachineLearningInstance;

public interface MachineLearningService {
	String evaluateInstance(List<MachineLearningInstance> dbInstances, MachineLearningInstance instance)
			throws Exception;
}
