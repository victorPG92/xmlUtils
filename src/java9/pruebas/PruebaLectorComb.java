package java9.pruebas;

import java9.bean.XMlStructure2;
import java9.readers.LectorXMlNormal;

public class PruebaLectorComb
{

	public static void main(String[] args)
	{
		LectorXMlNormal l= new LectorXMlNormal();
		
		
		String prueba1="C:\\Users\\vpenit\\eclipse-workspace\\java9\\resources\\prueba1.xml";
		String prueba2="C:\\Users\\vpenit\\Documents\\kpsat029\\artifact\\libraries\\KPSARCTVIMPL\\pom.xml";

		XMlStructure2 nodo = l.leerXml(prueba2);
	
	
		System.out.println(nodo.toString());
	
	}
}
