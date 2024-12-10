package com.example.controllers;


import com.example.entities.Emission;
import com.example.services.EmissionService;
import org.springframework.beans.factory.annotation.Autowired;   //inject dependencies automatically
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController   //handles http requests
@RequestMapping("/api/emissions")    //maps http requests to the class
public class EmissionController {

	@Autowired   //injects the object into the class, simplifies wiring up dependency
	private EmissionService emissionService;
	
	
	//get all emissions
	@GetMapping
	public List<Emission> getAllEmissions() {
		return emissionService.getAllEmissions();
	}
	
	
	//create a new emission
	@PostMapping 
	public Emission createEmission(@RequestBody Emission emission) {
		return emissionService.createEmission(emission);
	}
	
	
	//delete an emission
	@DeleteMapping("/{id}")
	public String deleteEmission(@PathVariable Long id) {
		emissionService.deleteEmission(id);
		return "Emission has been deleted";
	}
}
