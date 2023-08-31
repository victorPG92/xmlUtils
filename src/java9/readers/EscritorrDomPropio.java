package java9.readers;


import java.util.List;

import java9.bean.INodoXMl;
import java9.bean.NodoXML2;
import java9.dtos.Build;
import java9.dtos.Dependency;
import java9.dtos.PomXml;
import java9.dtos.Project;
import xml.NodoXml;

public class EscritorrDomPropio
{

	public INodoXMl escribirPom(PomXml pomXml)
	{		
			
		return creaNodoProjectNodo(pomXml);
			
			
	
	}

	private INodoXMl creaNodoProjectNodo(PomXml pomXml)
	{
		INodoXMl projectNode = new NodoXML2("project");
		
		Project project = pomXml.getProject();
		projectNode.setAtributos(project.getAtributos());
		
		
		addHijoIfNotNull(projectNode, creaNodoTagNameConText("artifactId",project.getArtifactId()));	
		addHijoIfNotNull(projectNode, creaNodoTagNameConText("description",project.getDescription()));		
		addHijoIfNotNull(projectNode, creaNodoTagNameConText("groupId",project.getGroupId()));		
		addHijoIfNotNull(projectNode, creaNodoTagNameConText("modelVersion",project.getModelVersion()));		
		addHijoIfNotNull(projectNode, creaNodoTagNameConText("name",project.getName()));		
		addHijoIfNotNull(projectNode, creaNodoTagNameConText("packaging",project.getPackaging()));		
		addHijoIfNotNull(projectNode, creaNodoTagNameConText("parent",project.getParent()));		
		addHijoIfNotNull(projectNode, creaNodoTagNameConText("version",project.getVersion()));	
		
		/**
		if(project.getDependencies()!=null)
			projectNode.addHijo(creaDependenciesXml(project.getDependencies()));	
			*/
		addHijoIfNotNull(projectNode,creaDependenciesXml(project.getDependencies()));
		projectNode.addHijo(creaBuildXMl(project.getBuild()));
				
				
			
				
				
		
			
		return projectNode;
				
		
	}

	
	public static void addHijoIfNotNull(INodoXMl padre, INodoXMl hijo)
	{
		if(padre!=null && hijo !=null)
		{
			padre.addHijo(hijo);
			
		}
	}
	
	
	public static INodoXMl creaNodoTagNameConText( String tagname, String text)
	{	
		if(text==null)
			return null;
		
		INodoXMl nodo = new NodoXML2(tagname);
		nodo.setText(text);	
		
		return nodo;
	}
	
	public static INodoXMl creaDependenciesXml( List<Dependency>dependencies)
	{
		INodoXMl nodo  = new NodoXML2("dependencies");
		for (Dependency dependency : dependencies) {
			nodo.addHijo(creaDependencyXml(dependency));
		}
		
		return nodo;
	}
	
	public static INodoXMl creaDependencyXml( Dependency dep)
	{
		
		INodoXMl nodo  = new NodoXML2("dependency");
		
		nodo.addHijo(creaNodoTagNameConText("groupId", dep.getGroupId()));
		nodo.addHijo(creaNodoTagNameConText("artifactId", dep.getArtifactId()));
		nodo.addHijo(creaNodoTagNameConText("version", dep.getVersion()));
		
		return nodo;
	}
	
	public static INodoXMl creaBuildXMl(Build build)
	{
		
		INodoXMl nodo = new NodoXML2("build");
	
		
		
		return nodo;
	}
	
	
	
	
}
