package com.example.services;


import com.example.entities.Emission;
import com.example.parser.JsonParser;
import com.example.parser.XmlParser;
import com.example.repositories.EmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;  //inject dependencies automatically
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class EmissionService {
	
	@Autowired
	private EmissionRepository emissionRepository;
    private JsonParser jsonParser;   //injected jsonparser
    private XmlParser xmlParser;     //injected xmlparser
    
	
	//create and update emission
	public Emission createEmission(Emission emission) {
		if (emission.getValue() <= 0) {
			throw new IllegalArgumentException("Error - Emission must be greater than 0");
		}
		
		if (emission.getYear()!= 2023) {
			throw new IllegalArgumentException("Error - Year must be 2023");
		}
		
		if (!"WEM".equalsIgnoreCase(emission.getScenario())) {
			throw new IllegalArgumentException("Error - Must have WEM");
		}
		return emissionRepository.save(emission);
	}
	
	
	//get all emissions
	public List<Emission> getAllEmissions() {
		return emissionRepository.findAll();
	}

	
	//deletes the emission using the ID
	public void deleteEmission(Long id) {
		if (emissionRepository.existsById(id)) {
			emissionRepository.deleteById(id);
		}
		else {
			throw new IllegalArgumentException("Emission with ID: " + id + "not found");
		}
	}
	
	
	public void parseAndSaveXml(String filePath) {
		try { 
			xmlParser.parseAndSaveXml(filePath);   //delegates xml parsing to parser
		} catch (Exception e) {
			throw new RuntimeException("Error - Failed to Parse Data");
		}
	}
	
	
	public void parseAndSaveJson(String filePath) {
		try {
			jsonParser.parseAndSaveJson(filePath);  //delegates work of json file
		} catch (Exception e) {
			throw new RuntimeException("Error - Couldn't save JSON");
		}
	}
	
}
