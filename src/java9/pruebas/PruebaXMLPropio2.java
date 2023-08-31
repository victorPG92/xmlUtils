package java9.pruebas;


import java9.bean.INodoXMl;
import java9.dtos.PomXml;
import java9.readers.EscritorrDomPropio;
import java9.readers.LectorDomPropio;
import java9.tostring.Node2StringSeparado2ResumirText;

public class PruebaXMLPropio2
{
	
	public static void mostrarXML(String file)
	{
		LectorDomPropio l= new LectorDomPropio();
		PomXml pom = l.leerDom(file);
		
		
		
		EscritorrDomPropio e = new EscritorrDomPropio();		
		INodoXMl xml = e.escribirPom( pom);
		
		Node2StringSeparado2ResumirText toXml= new Node2StringSeparado2ResumirText();
		System.out.println(toXml.node2String(xml));
		
		/**
		MostradorXML m= new  MostradorXML(new EscritorConsola());
		m.escribeNodo(new EscritorConsola(), xml);
		*/
		System.out.println();
	}
	
	public static void main(String[] args)
	{
		
		//mostrarXML("C:\\Users\\vpenit\\eclipse-workspace\\PomEditer\\pom.xml");
		
		mostrarXML("C:\\Users\\vpenit\\Documents\\kpsat029\\artifact\\libraries\\KPSARCTVIMPL\\pom.xml");

	}

}
