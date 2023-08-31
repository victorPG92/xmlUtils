package java9.bean;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import java9.tostring.Node2StringSeparado;
import java9.tostring.Node2StringSeparado2ResumirText;

public class NodoXML2 extends AbstractNodo
{
	protected String tagname;

	protected String id;


	protected List<INodoXMl> hijos = new ArrayList<>();

	protected String text;

	protected Map<String, String> atributos = new LinkedHashMap<>();

	protected Node2StringSeparado node2String = new Node2StringSeparado2ResumirText();
	
	
	
	public NodoXML2(String tagname)
	{
		this();
		this.tagname = tagname;
	}

	public NodoXML2(String tagname, String id)
	{
		this();
		this.tagname = tagname;
		this.id = id;
	}
	
	
	public static INodoXMl creaNodoComentario(String comentario)
	{
		INodoXMl com = new NodoXML2();
		com.setComentario(comentario);
		
		return com;
	}
	
	
	
	
	

	@Override
	public void addAtributo(String nombreAtrib, String valorAtrib)
	{
		if (nombreAtrib != null)
			this.atributos.put(nombreAtrib, valorAtrib);
	}

	@Override
	public void addHijo(INodoXMl hijo)
	{
		if (hijo != null) {
			hijo.setPadre(this);
			this.hijos.add(hijo);
		}
	}

	@Override
	public INodoXMl addHijo(String tagNameHijo)
	{
		NodoXML2 hijo = new NodoXML2(tagNameHijo);
		addHijo(hijo);
		return hijo;
	}

	@Override
	public String toString()
	{
		return node2String.node2String(this);
	}

	@Override
	public Stream<INodoXMl> getElementsByTagNameStream(String tagname)
	{
		return this.hijos.stream().filter(h -> tagname.equals(h.getTagname()));
	}

	@Override
	public List<INodoXMl> getElementsByTagName(String tagname)
	{
		/**
		 * List<NodoXML2> l = new ArrayList<>();
		getElementsByTagNameStream(tagname).forEach(h -> l.add(h));
		return l;
		 * 
		 * 
		 */
		
		return getElementsByTagNameStream(tagname).collect(Collectors.toList());
	}

	@Override
	public int numHijos()
	{
		return this.hijos.size();
	}

	@Override
	public boolean tieneHijos()
	{
		return (numHijos() > 0);
	}

	@Override
	public int numAtribs()
	{
		return this.atributos.size();
	}

	@Override
	public boolean tieneAtribs()
	{
		return (numAtribs() > 0);
	}

	@Override
	public INodoXMl getFirstChild()
	{
		if (this.hijos.isEmpty())
			return null;
		return this.hijos.get(0);
	}

	@Override
	public INodoXMl getLastChild()
	{
		if (this.hijos.isEmpty())
			return null;
		return this.hijos.get(this.hijos.size());
	}

	@Override
	public INodoXMl getHijo(int i)
	{
		if (i >= 0 && i < this.hijos.size())
			return this.hijos.get(i);
		return null;
	}

	@Override
	public String getAtributo(String nombreAtrib)
	{
		return this.atributos.get(nombreAtrib);
	}

	@Override
	public boolean hasAtributo(String nombreAtrib)
	{
		return this.atributos.containsKey(nombreAtrib);
	}

	@Override
	public boolean hasAttributes()
	{
		return !this.atributos.isEmpty();
	}

	@Override
	public boolean hasChilds()
	{
		return (numHijos() > 0);
	}

	@Override
	public boolean hasAtribs()
	{
		return (numAtribs() == 0);
	}

	@Override
	public String getTagname()
	{
		return this.tagname;
	}

	@Override
	public void setTagname(String tagname)
	{
		this.tagname = tagname;
	}

	@Override
	public String getId()
	{
		return this.id;
	}

	@Override
	public void setId(String id)
	{
		this.id = id;
	}



	@Override
	public List<INodoXMl> getHijos()
	{
		return this.hijos;
	}

	@Override
	public void setHijos(List<INodoXMl> hijos)
	{
		this.hijos = hijos;
	}

	@Override
	public String getText()
	{
		return this.text;
	}

	@Override
	public void setText(String text)
	{
		this.text = text;
	}

	@Override
	public Map<String, String> getAtributos()
	{
		return this.atributos;
	}

	@Override
	public void setAtributos(Map<String, String> atributos)
	{
		this.atributos = atributos;
	}
	
	

	@Override
	public Node2StringSeparado getNode2String()
	{
		return node2String;
	}

	@Override
	public void setNode2String(Node2StringSeparado node2String)
	{
		this.node2String = node2String;
	}
	
	

	

	@Override
	public String getComentario()
	{
		return null;
	}

	@Override
	public void setComentario(String comentario)
	{
		
	}

	public NodoXML2()
	{
	}
}
