package java9.processor;

import java.util.HashMap;
import java.util.Map;

import java9.dtos.PomXml;
import xml.NodoXml;

public class CorrectorXLMNS
{

	public void corrige(PomXml pom)
	{

		
		pom.getProject().setAtributos(creaMapaAtributosProject());

	}
	
	
	public Map<String, String> creaMapaAtributosProject()
	{
		Map<String, String> atributos= new HashMap<String, String>();
		
		
		atributos.put("xmlns", "http://maven.apache.org/POM/4.0.0");
		atributos.put("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
		atributos.put("xsi:schemaLocation", "http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd");

		
		
		return atributos;
		
		
	}
	
	
	
	
}
