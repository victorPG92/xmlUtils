package java9.dtos;
import java.util.List;
import java.util.Map;



public class Project
{

	private String modelVersion;
	
	private String parent;
	
	private String groupId;
	private String artifactId;
	private String name;
	private String version;
	
	
	private String packaging;
	private String description;
	
	//properties   osgi.version.manifest
	
	private Build build;
	
	

	private List<Dependency> dependencies;
	
	Map<String, String> atributos;
	
	
	public Map<String, String> getAtributos()
	{
		return atributos;
	}
	public void setAtributos(Map<String, String> atributos)
	{
		this.atributos = atributos;
	}
	public String getModelVersion()
	{
		return modelVersion;
	}
	public void setModelVersion(String modelVersion)
	{
		this.modelVersion = modelVersion;
	}
	public String getGroupId()
	{
		return groupId;
	}
	public void setGroupId(String groupId)
	{
		this.groupId = groupId;
	}
	public String getArtifactId()
	{
		return artifactId;
	}
	public void setArtifactId(String artifactId)
	{
		this.artifactId = artifactId;
	}
	public String getVersion()
	{
		return version;
	}
	public void setVersion(String version)
	{
		this.version = version;
	}
	public Build getBuild()
	{
		return build;
	}
	public void setBuild(Build build)
	{
		this.build = build;
	}
	public List<Dependency> getDependencies()
	{
		return dependencies;
	}
	public void setDependencies(List<Dependency> dependencies)
	{
		this.dependencies = dependencies;
	}
	
	
	public String getParent()
	{
		return parent;
	}
	public void setParent(String parent)
	{
		this.parent = parent;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getPackaging()
	{
		return packaging;
	}
	public void setPackaging(String packaging)
	{
		this.packaging = packaging;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	/**
	private String modelVersion;
	private String modelVersion;
	private String modelVersion;
	private String modelVersion;
	private String modelVersion;
*/
	
	
	
	
}
