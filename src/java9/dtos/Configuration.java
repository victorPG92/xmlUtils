package java9.dtos;



public class Configuration
{

	private String source;
	private String target;
	
	private Instructions instructions;
	
	
	// de execution
	private Tasks tasks;
	
	
	public String getSource()
	{
		return source;
	}
	public void setSource(String source)
	{
		this.source = source;
	}
	public String getTarget()
	{
		return target;
	}
	public void setTarget(String target)
	{
		this.target = target;
	}
	
	

	
}
