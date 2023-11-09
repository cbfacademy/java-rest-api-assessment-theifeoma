package com.cbfacademy.apiassessment;

import com.cbfacademy.apiassessment.helpers.CSVDataConverter;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;


@SpringBootApplication
public class App {

	public static void main(String[] args) {

		//SpringApplication.run(App.class, args);
		CSVDataConverter csvDataConverter = new CSVDataConverter();
		List<String> csvFile = List.of("src/main/resources/csvFiles/clientAddress.csv", "src/main/resources/csvFiles/clientDetails.csv", "src/main/resources/csvFiles/employeeDetails.csv", "src/main/resources/csvFiles/legalDetails.csv", "src/main/resources/csvFiles/tradeDetails.csv");
		String jsonFile = "src/main/resources/jsonFiles/repo.json";
		csvDataConverter.convertCSVToJson(csvFile, jsonFile);

	}


}
