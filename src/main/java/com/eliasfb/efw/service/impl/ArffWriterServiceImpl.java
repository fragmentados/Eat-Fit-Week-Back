package com.eliasfb.efw.service.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.eliasfb.efw.model.MachineLearningInstance;
import com.eliasfb.efw.service.ArffWriterService;

@Service
public class ArffWriterServiceImpl implements ArffWriterService {

	@Override
	public void writeTrainingDataSetFile(List<MachineLearningInstance> dbInstances) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("training.arff"));
		this.writeHeader(writer, dbInstances, "Training");
		for (MachineLearningInstance dbInstance : dbInstances) {
			writer.write("'" + dbInstance.getWeekDay() + "','" + dbInstance.getMealName() + "','"
					+ dbInstance.getDishName() + "'\n");
		}
		writer.close();
	}

	@Override
	public void writeTestDataSetFile(List<MachineLearningInstance> dbInstances, MachineLearningInstance dbInstance)
			throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("test.arff"));
		this.writeHeader(writer, dbInstances, "Test");
		writer.write("'" + dbInstance.getWeekDay() + "','" + dbInstance.getMealName() + "',?");
		writer.close();
	}

	private void writeHeader(BufferedWriter writer, List<MachineLearningInstance> dbInstances, String relationName)
			throws IOException {
		writer.write("@relation " + relationName + "\n");
		writer.write("\n");
		writer.write(
				"@attribute weekDay {" + formatStringListAsString(getWeekDaysFromDbInstances(dbInstances)) + "}\n");
		writer.write(
				"@attribute mealName {" + formatStringListAsString(getMealNamesFromDbInstances(dbInstances)) + "}\n");
		writer.write(
				"@attribute dishName {" + formatStringListAsString(getDishNamesFromDbInstances(dbInstances)) + "}\n");
		writer.write("\n");
		writer.write("@data\n");
	}

	private static String formatStringListAsString(List<String> stringList) {
		return "'" + String.join("','", stringList) + "'";
	}

	private static List<String> getDishNamesFromDbInstances(List<MachineLearningInstance> dbInstances) {
		return dbInstances.stream().map(dbi -> dbi.getDishName()).distinct().collect(Collectors.toList());
	}

	private static List<String> getMealNamesFromDbInstances(List<MachineLearningInstance> dbInstances) {
		return dbInstances.stream().map(dbi -> dbi.getMealName()).distinct().collect(Collectors.toList());
	}

	private static List<String> getWeekDaysFromDbInstances(List<MachineLearningInstance> dbInstances) {
		return dbInstances.stream().map(dbi -> dbi.getWeekDay()).distinct().collect(Collectors.toList());
	}

}
