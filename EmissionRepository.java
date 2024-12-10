package com.example.repositories;


import com.example.entities.Emission;
import org.springframework.data.jpa.repository.JpaRepository;  //imports JR interface
import org.springframework.stereotype.Repository;  //imports Repository annotation from Spring Frmework
import java.util.List;

@Repository
public interface EmissionRepository extends JpaRepository<Emission, Long>{

	//this finds emissions for a specific category
	List<Emission> findByCategoryId(Long categoryId);
	
	//this finds the emissions for a specific year
	List<Emission> findByYear(int year);
	
	//this finds the emissions by scenario
	List<Emission> findByScenario(String scenario);
}
