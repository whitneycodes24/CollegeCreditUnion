package com.example.parser;


import com.example.entities.*;
import com.example.services.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class JsonParser {

	@Autowired
	private EmissionService emissionService;
	
	public void parseAndSaveJson(String filePath) {
		ObjectMapper objectMapper = new ObjectMapper();
		
		//this reads and maps the json file to emission object
		try {
			List<Emission> emissions = objectMapper.readValue(
					new File(filePath),
					new TypeReference<List<Emission>>() {} 
					);
			
			int validEntries = 0;
			
			//rules for the valid emissions
			for (Emission emission : emissions) {
				if (emission.getValue() > 0 && emission.getYear() == 2023 && 
						"WEM".equalsIgnoreCase(emission.getScenario())) {
					emissionService.createEmission(emission);
					validEntries++;
				}
			}
			
			System.out.println("Valid Entries Saved" + validEntries);
			
		} catch (IOException e) {
			System.out.println("Error - Failed to Load");
		}
	}
}
