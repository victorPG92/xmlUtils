package java9.dtos;
import java.util.List;



public class Build implements ElementoPom
{
	
	private String sourceDirectory;

	private String finalName;
	
	private List<Plugin> plugins;

	public String getSourceDirectory()
	{
		return sourceDirectory;
	}

	public void setSourceDirectory(String sourceDirectory)
	{
		this.sourceDirectory = sourceDirectory;
	}

	public String getFinalName()
	{
		return finalName;
	}

	public void setFinalName(String finalName)
	{
		this.finalName = finalName;
	}

	public List<Plugin> getPlugins()
	{
		return plugins;
	}

	public void setPlugins(List<Plugin> plugins)
	{
		this.plugins = plugins;
	}
	
	
	
}
