package java9.readers;


import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.xml.crypto.XMLStructure;

import java9.dtos.Build;
import java9.dtos.Dependency;
import java9.dtos.PomXml;
import java9.dtos.Project;
import xml.ConstructorArbolXML;
import xml.NodoXml;

public class LectorDomPropio
{

	public PomXml leerDom(String xmlFile)
	{
		PomXml pom= new PomXml();
		System.out.println("inicio");
		ConstructorArbolXML c = new ConstructorArbolXML();
		try {
			 xml.XMLStructure xmlStr = c.crear(xmlFile);
			
			
			NodoXml projectNode = xmlStr.getRaiz();
			Project project = new Project();
			// los atributos los coge mal porque deberia ir caracter a caracter, en vez de separa por espacio
			Map<String, String> atribs = projectNode.getAtributos();
			
			System.out.println("atribs de project");
			for (Map.Entry<String, String> entry : atribs.entrySet()) {
				String key = entry.getKey();
				String val = entry.getValue();
				
				
				System.out.println(key + "->"+ val);
				
				
			}
			
			project.setAtributos(atribs);
			
			project.setArtifactId(getTextByTagName(projectNode,"artifactId"));
			project.setBuild(creaBuild(projectNode));
			project.setDescription(getTextByTagName(projectNode,"description"));
			project.setGroupId(getTextByTagName(projectNode,"groupId"));
			project.setModelVersion(getTextByTagName(projectNode,"modelVersion"));
			project.setName(getTextByTagName(projectNode,"name"));
			project.setPackaging(getTextByTagName(projectNode,"packaging"));
			project.setParent(getTextByTagName(projectNode,"parent"));
			project.setVersion(getTextByTagName(projectNode,"version"));
			
			
			
			extraeDependencies(projectNode, project);
			
		
			pom.setProject(project);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		return pom;
	}

	private void extraeDependencies(NodoXml projectNode, Project project)
	{
		List<NodoXml> dependenciesNodes = projectNode.getElementsByTagName("dependencies");
		NodoXml dependenciesNode = dependenciesNodes.get(0);
		
		
		System.out.println("dependencies:");
		dependenciesNode.getElementsByTagNameStream("dependency")
		.map(LectorDomPropio::creaDependency)
		.map(LectorDomPropio::toStringDep)
		.forEach(System.out::println);

		
		project.setDependencies(
				dependenciesNode.getElementsByTagNameStream("dependency")
				.map(LectorDomPropio::creaDependency)
				.collect(Collectors.toList())
		);
	}{
}
	
	public static String getTextByTagName(NodoXml nodo, String tagname)
	{
		
		List<NodoXml> deps = nodo.getElementsByTagName(tagname);
		if(deps!=null && !deps.isEmpty())
		return deps.get(0).getText();
		
		else
			return null;
}
	
	public static Dependency creaDependency(NodoXml nodo)
	{
		Dependency d= new Dependency();
		
		d.setGroupId(getTextByTagName(nodo,"groupId"));
		d.setArtifactId(getTextByTagName(nodo,"artifactId"));
		d.setVersion(getTextByTagName(nodo,"version"));
		
		
		return d;
	}
	
	public static Build creaBuild(NodoXml nodo)
	{
		Build build= new Build();
		
		
		return build;
	}
	
	public static String toStringDep(Dependency d)
	{
		return d.getGroupId() +" "+d.getArtifactId() + " "+ d.getVersion() + d.getScope();
	}
	
	
}
