package com.example.parser;


import com.example.entities.Emission;
import com.example.services.EmissionService;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class XmlParser {

	private static EmissionService emissionService;  //emissionService is injected
	
	public static void main(String[] args) {
		
		try {
			
			//this loads the xml file
			File xmlFile = new File("src/main/resources/greenhouse-gas-emissions-predictions.xml");
			
			
			//creates the documentbuilder
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(xmlFile);
			
			
			System.out.println("Root element: " + doc.getDocumentElement().getNodeName());  //prints root element
			NodeList rows = doc.getElementsByTagName("row");   //gets elements in the row 
			
			
			//list for valid emissions 
			List<Emission> emissions = new ArrayList<>();
			int validEntries = 0;  //this counts the valid entries
			
			for (int i = 0; i < rows.getLength(); i++) {
				
				Node row = rows.item(i);
				
				System.out.println("\nCurrent Element: " + row.getNodeName());
				
				if (row.getNodeType() == Node.ELEMENT_NODE) {
					
					Element elem = (Element) row;
					
					try {
						
					String category = elem.getElementsByTagName("Category__1_3").item(0).getTextContent();
					String description = elem.getElementsByTagName("description").item(0).getTextContent();
					double value = Double.parseDouble(elem.getElementsByTagName("value").item(0).getTextContent());
					String scenario = elem.getElementsByTagName("scenario").item(0).getTextContent();
					int year = Integer.parseInt(elem.getElementsByTagName("year").item(0).getTextContent());
					
					
					//rules to apply
					if (value > 0 && year == 2023 && "WEM".equalsIgnoreCase(scenario)) {
						Emission emission = new Emission(category, description, value, scenario, year);
						emissions.add(emission);
						validEntries++;
					} else {
						System.out.println("Error - Invalid Data");
					}
					
					} catch (Exception e) {
						
						System.out.println("Error");
					}
				}
			}
				
				//save the valid emissions
				for (Emission emission : emissions) {
					emissionService.createEmission(emission);
				}
				
				
				System.out.println("XML Parsing is Complete. Valid Entries: " + validEntries);
			} catch (Exception e) {
				System.out.println("Error");
			}
		}
		}