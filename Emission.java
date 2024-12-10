package com.example.entities;


import jakarta.persistence.*;

@Entity  //model layer
public class Emission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	

	@Column(nullable = false)  //makes sure there is no null values
	private String category;  
	
	@Column(nullable = false, length = 1000)
	private String description;
	
	@Column(nullable = false) 
	private double value; 
	
	@Column(nullable = false)
	private String scenario; 
	
	@Column(nullable = false)
	private int year; 
	
	
	//constructor
	 public Emission() {}

	    public Emission(String category, String description, double value, String scenario, int year) {
	        this.category = category;
	        this.description = description;
	    	this.value = value;
	        this.scenario = scenario;
	        this.year = year;
	    }

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public double getValue() {
			return value;
		}

		public void setValue(double value) {
			this.value = value;
		}

		public String getScenario() {
			return scenario;
		}

		public void setScenario(String scenario) {
			this.scenario = scenario;
		}

		public int getYear() {
			return year;
		}

		public void setYear(int year) {
			this.year = year;
		}
}
