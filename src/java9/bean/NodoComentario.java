package java9.bean;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import java9.tostring.Node2StringSeparado;

public class NodoComentario extends AbstractNodo
{

	
	
	protected String comentario;
	
	
	
	public NodoComentario(String com)
	{
		comentario= com;
		isComentario=true;
	}
	
	
	
	
	@Override
	public void addAtributo(String nombreAtrib, String valorAtrib) throws UnsupportedOperationException
	{
		
		
	}

	@Override
	public void addHijo(INodoXMl hijo) throws UnsupportedOperationException
	{
		
	}

	@Override
	public INodoXMl addHijo(String tagNameHijo)  throws UnsupportedOperationException
	{
		
		return null;
	}

	@Override
	public Stream<INodoXMl> getElementsByTagNameStream(String tagname)
	{
		return null;
	}

	@Override
	public List<INodoXMl> getElementsByTagName(String tagname)
	{
		return null;
	}

	@Override
	public int numHijos()
	{
		return 0;
	}

	@Override
	public boolean tieneHijos()
	{
		return false;
	}

	@Override
	public int numAtribs()
	{
		return 0;
	}

	@Override
	public boolean tieneAtribs()
	{
		return false;
	}

	@Override
	public INodoXMl getFirstChild()
	{
		return null;
	}

	@Override
	public INodoXMl getLastChild()
	{
		return null;
	}

	@Override
	public INodoXMl getHijo(int i)
	{
		return null;
	}

	@Override
	public String getAtributo(String nombreAtrib)
	{
		return null;
	}

	@Override
	public boolean hasAtributo(String nombreAtrib)
	{
		return false;
	}

	@Override
	public boolean hasAttributes()
	{
		return false;
	}

	@Override
	public boolean hasChilds()
	{
		return false;
	}

	@Override
	public boolean hasAtribs()
	{
		return false;
	}

	@Override
	public String getTagname()
	{
		return null;
	}

	@Override
	public void setTagname(String tagname)  throws UnsupportedOperationException
	{
		
	}

	@Override
	public String getId()
	{
		return null;
	}

	@Override
	public void setId(String id)  throws UnsupportedOperationException
	{
		
	}


	@Override
	public List<INodoXMl> getHijos()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setHijos(List<INodoXMl> hijos)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getText()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setText(String text)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, String> getAtributos()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAtributos(Map<String, String> atributos)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public Node2StringSeparado getNode2String()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setNode2String(Node2StringSeparado node2String)
	{
		// TODO Auto-generated method stub
		
	}



	@Override
	public String getComentario()
	{
		
		return comentario;
	}

	@Override
	public void setComentario(String comentario)
	{
		this.comentario=comentario;		
	}

	
	@Override
	public String toString()
	{
		return "<!--" +  comentario + "-->";
	}
}
