package com.eliasfb.efw.service;

import java.io.IOException;
import java.util.List;

import com.eliasfb.efw.model.MachineLearningInstance;

public interface ArffWriterService {

	void writeTrainingDataSetFile(List<MachineLearningInstance> dbInstances) throws IOException;

	void writeTestDataSetFile(List<MachineLearningInstance> dbInstances, MachineLearningInstance dbInstance)
			throws IOException;
}
