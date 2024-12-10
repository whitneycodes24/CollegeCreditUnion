package com.example.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.services.EmissionService;
import com.example.entities.Emission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;


@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private Emission serviceEmission;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {  //allows you to accept a variable number of arugments cuz we dk
		
		//file path for json
		String jsonFilePath = "src/main/resources/GreenhouseGasEmissions.json";
		System.out.println("Loading the Data from JSON: " + jsonFilePath);
		
		try {
			//method call for the parsing
			serviceEmission.parseAndSaveJson(jsonFilePath);
			System.out.println("JSON Data has been loaded");
		} catch (Exception e) {
			System.out.println("Error - Failed to load");
		}
		
		
		//file path for XML
		String xmlFilePath = "src/main/resources/greenhouse-gas-emissions-predictions.xml";
		System.out.println("Loading the Data from XML" + xmlFilePath);
		
		try {
			serviceEmission.parseAndSaveXml(xmlFilePath);
			System.out.println("XML Data has been loaded");
		} catch (Exception e) {
			System.out.println("Error - Failed to load");
		}
	}

}
