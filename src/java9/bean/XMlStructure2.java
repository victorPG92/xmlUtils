package java9.bean;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class XMlStructure2
{
	INodoXMl raiz;

	Map<String, INodoXMl> nodosById = new LinkedHashMap<>();

	boolean standAlone = false;

	String encoding = "UTF-8";

	String version = "1.0";

	public String toString()
	{
		return "<?xml version=\""+version+"\" encoding=\""+encoding+"\" standalone=\"" + (this.standAlone ? "yes" : "no")
				+ "\"?>\n" + this.raiz.toString();
	}

	public INodoXMl getRaiz()
	{
		return this.raiz;
	}

	public void setRaiz(INodoXMl raiz)
	{
		this.raiz = raiz;
	}

	public Map<String, INodoXMl> getNodosById()
	{
		return this.nodosById;
	}

	public List<INodoXMl> getNodosByTagName(String tagname)
	{
		return this.raiz.getElementsByTagName(tagname);
	}

	public boolean isStandAlone()
	{
		return this.standAlone;
	}

	public void setStandAlone(boolean standAlone)
	{
		this.standAlone = standAlone;
	}

	public String getEncoding()
	{
		return this.encoding;
	}

	public void setEncoding(String encoding)
	{
		this.encoding = encoding;
	}

	public String getVersion()
	{
		return this.version;
	}

	public void setVersion(String version)
	{
		this.version = version;
	}
}
