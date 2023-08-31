package java9.dtos;
import java.util.List;



public class Plugin
{

	private String artifactId;
	private String version;
	private Configuration configuration;
	
	private String extensions;
	
	private List<Execution> executions;

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

	public Configuration getConfiguration()
	{
		return configuration;
	}

	public void setConfiguration(Configuration configuration)
	{
		this.configuration = configuration;
	}

	public String getExtensions()
	{
		return extensions;
	}

	public void setExtensions(String extensions)
	{
		this.extensions = extensions;
	}

	public List<Execution> getExecutions()
	{
		return executions;
	}

	public void setExecutions(List<Execution> executions)
	{
		this.executions = executions;
	}

	
	


	
}
