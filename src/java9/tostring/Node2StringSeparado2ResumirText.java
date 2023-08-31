package java9.tostring;

import java9.bean.INodoXMl;
import xml.NodoXml;

public class Node2StringSeparado2ResumirText extends  Node2StringSeparado
{
	@Override
	protected void writeHijos(INodoXMl node, int nivel, StringBuilder sb)
	{
		if (oTieneHijosOTieneText(node))
		{
			sb.append(">");
			
			if (!node.getHijos().isEmpty()) {
				sb.append("\n");
				writeHijo(node, nivel, sb);
				sb.append(tabs(nivel));
			} else if (node.getText() != null || !node.getText().isEmpty()) 
			{
				if(noDebeColapsarTexto (node ))
				{	
					sb.append("\n");	
					
					sb.append(tabs(nivel));
				}
				writeText(node, nivel, sb);
			}
			
			sb.append("</");
			sb.append(node.getTagname());
			sb.append(">");
		} else {
			sb.append("/>");
		}
	}
	
	public static boolean noDebeColapsarTexto(INodoXMl node)
	{
		return node.getText().length()>=50;
	}
	
	@Override
	protected void writeTextSimple(INodoXMl node, int nivel, StringBuilder sb)
	{
		String text=node.getText();
		if(noDebeColapsarTexto(node))
		{
			sb.append(tabs(nivel + 1));
		}
		sb.append(quitarInicio(text.trim()));
		
	}
	
	@Override
	protected void writeText(INodoXMl node, int nivel, StringBuilder sb)
	{
		writeTextSimple(node, nivel, sb);
		if(noDebeColapsarTexto(node))
		{
			sb.append("\n");
			sb.append(tabs(nivel));

		
		}
	}
}
