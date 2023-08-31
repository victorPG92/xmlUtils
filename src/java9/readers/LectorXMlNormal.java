package java9.readers;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java9.bean.INodoXMl;
import java9.bean.NodoComentario;
import java9.bean.NodoXML2;
import java9.bean.XMlStructure2;
import xml.XMLStructure;

public class LectorXMlNormal
{

	public XMlStructure2 leerXml(String fichero)
	{

		XMlStructure2 xml = new XMlStructure2();

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document documento = null;

		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			documento = builder.parse(new File(fichero));

			Node nodoRaiz = documento.getFirstChild();
			
		
			xml.setRaiz(convierteNode(nodoRaiz) );
			
			
			
			
			
			
			return xml;

		} catch (Exception spe) 
		{
			spe.printStackTrace();
			return null;
			// Algún tipo de error: fichero no accesible, formato de XML incorrecto, etc.
		}
	}
	
	/**
	 * El primer nodo es normal.
	 * @param node
	 * @return
	 */
	protected INodoXMl convierteNode(Node node)
	{
		INodoXMl nodo = new NodoXML2();
		
		nodo.setTagname(node.getNodeName());
		
		asignaAtributos(node, nodo);
		
		
		
		
		NodeList listaNodosHijos = node.getChildNodes();
		for (int i = 0; i < listaNodosHijos.getLength(); i++) 
		{
			Node unNodeHijo = listaNodosHijos.item(i);
			
			String tagname= unNodeHijo.getNodeName();
			
			if("#text".equalsIgnoreCase(tagname))
			{
				nodo.setText(unNodeHijo.getTextContent());
			}
			else if("#comment".equals(tagname))
			{
				nodo.addHijo(new NodoComentario(unNodeHijo.getTextContent()));
			}
			else
			{
				INodoXMl unNodoHijo = convierteNode(unNodeHijo);
				nodo.addHijo(unNodoHijo);
			}
		}
		
		
		//nodo.setText(node.getTextContent());
		
		
		return nodo;
	}
	
	protected void asignaAtributos(Node unNodo,INodoXMl miNodoRaiz )
	{
		NamedNodeMap atributosNode = unNodo.getAttributes();
		if(atributosNode!=null)
		{
			for (int i = 0; i < atributosNode.getLength(); i++)
			{
				Node atributoNode = atributosNode.item(i);
				
				
				String nombreAtrib = atributoNode.getNodeName();
				String valueAtrib = atributoNode.getNodeValue();

				String textAtrib = atributoNode.getTextContent();

				
				System.out.println("atrib name: "+ nombreAtrib);
				System.out.println("atrib value: "+valueAtrib);

				System.out.println("atrib text: "+textAtrib);
				
				
				
				miNodoRaiz.addAtributo(nombreAtrib, valueAtrib);
				
				
			}
		}
		/**
		Node unAtributo = atributosNode.getNamedItem("nombre_atributo");
		String valorAtributo = unAtributo.getNodeValue();
		*/
	}
}
