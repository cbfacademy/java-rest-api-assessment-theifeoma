package com.cbfacademy.apiassessment;

import com.cbfacademy.apiassessment.entities.Client;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

import static com.cbfacademy.apiassessment.constants.Const.CSV_CLIENT_FILENAME;

@SpringBootApplication
public class App {

	public static void main(String[] args) {

		//SpringApplication.run(App.class, args);

		Csv csvHelper = new Csv(CSV_CLIENT_FILENAME);

		System.out.println("Employees in CSV file");

		System.out.println();

		List<Client> clients = csvHelper.readClientFromCsv();

		for (Client client : clients){
			System.out.println(client);
		}

		System.out.println();
		System.out.println("Employee information added");
		System.out.println();

		Client newClient = new Client(5L, "Hannah Bills", "Mike Jones", "hannah@yahoo.com", "7893266474", "23 Fish Street");

		csvHelper.addNewClientToClientCsv(newClient);

		//UPDATED CSV
		List<Client> clients1 = csvHelper.readClientFromCsv();

		for (Client client : clients1){
			System.out.println(client);
		}

//    System.out.println();
//    System.out.println("Employee name changed");
//    System.out.println();
//
//    findEmployeeByIdAndUpdateEmployeeName(1L, CSV_EMPLOYEE_FILENAME);

		//UPDATED CSV
		List<Client> clients2 = csvHelper.readClientFromCsv();

		System.out.println("EMPLOYEE AS STRING");

		csvHelper.convertEmployeeToStringForCSV(clients2);

//    for (Employee employee : clients2){
//      System.out.println(employee);
//    }

	}


}
