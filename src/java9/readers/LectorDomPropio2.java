package java9.readers;


import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import java9.bean.INodoXMl;
import java9.bean.XMlStructure2;
import java9.dtos.Build;
import java9.dtos.Dependency;
import java9.dtos.PomXml;
import java9.dtos.Project;
import xml.NodoXml;

public class LectorDomPropio2
{

	public PomXml leerDom(String xmlFile)
	{
		PomXml pom= new PomXml();
		System.out.println("inicio");
		LectorXMlNormal c = new LectorXMlNormal();
		//try {
			 XMlStructure2 xmlStr = c.leerXml(xmlFile);
			
			
			INodoXMl projectNode = xmlStr.getRaiz();
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
			
			
		/**} catch (IOException e) {
			e.printStackTrace();
		}*/
		
		
		
		
		
		
		return pom;
	}

	private void extraeDependencies(INodoXMl projectNode, Project project)
	{
		List<INodoXMl> dependenciesNodes = projectNode.getElementsByTagName("dependencies");
		INodoXMl dependenciesNode = dependenciesNodes.get(0);
		
		
		System.out.println("dependencies:");
		dependenciesNode.getElementsByTagNameStream("dependency")
		.map(LectorDomPropio2::creaDependency)
		.map(LectorDomPropio2::toStringDep)
		.forEach(System.out::println);

		
		project.setDependencies(
				dependenciesNode.getElementsByTagNameStream("dependency")
				.map(LectorDomPropio2::creaDependency)
				.collect(Collectors.toList())
		);
	}{
}
	
	public static String getTextByTagName(INodoXMl nodo, String tagname)
	{
		
		List<INodoXMl> deps = nodo.getElementsByTagName(tagname);
		if(deps!=null && !deps.isEmpty())
		return deps.get(0).getText();
		
		else
			return null;
}
	
	public static Dependency creaDependency(INodoXMl nodo)
	{
		Dependency d= new Dependency();
		
		d.setGroupId(getTextByTagName(nodo,"groupId"));
		d.setArtifactId(getTextByTagName(nodo,"artifactId"));
		d.setVersion(getTextByTagName(nodo,"version"));
		
		
		return d;
	}
	
	public static Build creaBuild(INodoXMl nodo)
	{
		Build build= new Build();
		
		
		return build;
	}
	
	public static String toStringDep(Dependency d)
	{
		return d.getGroupId() +" "+d.getArtifactId() + " "+ d.getVersion() + d.getScope();
	}
	
	
}
