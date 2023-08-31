package java9.bean;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import java9.tostring.Node2StringSeparado;

public interface INodoXMl
{

	void addAtributo(String nombreAtrib, String valorAtrib);

	void addHijo(INodoXMl hijo);

	INodoXMl addHijo(String tagNameHijo);

	String toString();

	Stream<INodoXMl> getElementsByTagNameStream(String tagname);

	List<INodoXMl> getElementsByTagName(String tagname);

	int numHijos();

	boolean tieneHijos();

	int numAtribs();

	boolean tieneAtribs();

	INodoXMl getFirstChild();

	INodoXMl getLastChild();

	INodoXMl getHijo(int i);

	String getAtributo(String nombreAtrib);

	boolean hasAtributo(String nombreAtrib);

	boolean hasAttributes();

	boolean hasChilds();

	boolean hasAtribs();

	String getTagname();

	void setTagname(String tagname);

	String getId();

	void setId(String id);

	INodoXMl getPadre();

	void setPadre(INodoXMl padre);

	List<INodoXMl> getHijos();

	void setHijos(List<INodoXMl> hijos);

	String getText();

	void setText(String text);

	Map<String, String> getAtributos();

	void setAtributos(Map<String, String> atributos);

	Node2StringSeparado getNode2String();

	void setNode2String(Node2StringSeparado node2String);

	boolean isComentario();

	void setIsComentario(boolean esComment);

	String getComentario();

	void setComentario(String comentario);

}