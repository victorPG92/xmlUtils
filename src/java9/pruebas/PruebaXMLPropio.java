package java9.pruebas;


import com.asistente.programacion.escritura.EscritorConsola;

import asistenteProgramacionXMLMostrar.MostradorXML;
import java9.bean.INodoXMl;
import java9.dtos.PomXml;
import java9.readers.EscritorrDomPropio;
import java9.readers.LectorDomPropio;
import xml.NodoXml;

public class PruebaXMLPropio
{
	
	public static void mostrarXML(String file)
	{
		LectorDomPropio l= new LectorDomPropio();
		PomXml pom = l.leerDom(file);
		
		
		
		EscritorrDomPropio e = new EscritorrDomPropio();		
		INodoXMl xml = e.escribirPom( pom);		
		System.out.println(xml.toString());
		
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
