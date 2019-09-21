package com.eliasfb.efw.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eliasfb.efw.model.MachineLearningInstance;
import com.eliasfb.efw.service.ArffWriterService;
import com.eliasfb.efw.service.MachineLearningService;

import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

@Service
public class MachineLearningServiceImpl implements MachineLearningService {

	@Autowired
	private ArffWriterService fileService;

	@Override
	public String evaluateInstance(List<MachineLearningInstance> dbInstances, MachineLearningInstance instance)
			throws Exception {

		// Write files for training and testing
		this.fileService.writeTrainingDataSetFile(dbInstances);
		this.fileService.writeTestDataSetFile(dbInstances, instance);

		// Read files for training and testing datasets
		ConverterUtils.DataSource source1 = new ConverterUtils.DataSource("training.arff");
		Instances trainDataset = source1.getDataSet();
		trainDataset.setClassIndex(trainDataset.numAttributes() - 1);
		ConverterUtils.DataSource source2 = new ConverterUtils.DataSource("test.arff");
		Instances testDataset = source2.getDataSet();
		testDataset.setClassIndex(testDataset.numAttributes() - 1);

		// NaiveBayse classification
		NaiveBayes nb = new NaiveBayes();
		nb.buildClassifier(trainDataset);

		// evaluate with the classifier the test instance
		double classIndex = nb.classifyInstance(testDataset.instance(0));

		// We delete the files generated for the machine learning algorithm
		// TODO EFB REVIEW WHY THE FILES ARE NOT BEING DELETED
		new File("training.arff").delete();
		new File("test.arff").delete();

		return testDataset.classAttribute().value((int) classIndex);
	}

}
