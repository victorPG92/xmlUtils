package java9.tostring;

import java.util.Map;

import java9.bean.INodoXMl;

public class Node2StringSeparado
{
	public String node2String(INodoXMl node)
	{
		return node2String(node, 0);
	}

	public String node2String(INodoXMl node, int nivel)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(tabs(nivel));
		sb.append("<");
		
		if(node.isComentario())
		{
			sb.append("!--");
			sb.append(node.getComentario());
			sb.append("-->");
			
		}
		else
		{
			sb.append(node.getTagname());
			
			sb = writeAtributos(node, sb);
			
			writeHijos(node, nivel, sb);
		}
		return sb.toString();
	}

	protected void writeHijos(INodoXMl node, int nivel, StringBuilder sb)
	{
		if (oTieneHijosOTieneText(node)) {
			sb.append(">");
			sb.append("\n");
			if (!node.getHijos().isEmpty()) {
				writeHijo(node, nivel, sb);
			} else if (node.getText() != null || !node.getText().isEmpty()) {
				writeText(node, nivel, sb);
			}
			sb.append(tabs(nivel));
			sb.append("</");
			sb.append(node.getTagname());
			sb.append(">");
		} else {
			sb.append("/>");
		}
	}

	protected boolean oTieneHijosOTieneText(INodoXMl node)
	{
		return !node.getHijos().isEmpty() || 
				(node.getText() != null && !node.getText().isEmpty());
	}

	protected void writeTextSimple(INodoXMl node, int nivel, StringBuilder sb)
	{
		String text=node.getText();
		sb.append(tabs(nivel + 1));
		sb.append(quitarInicio(text.trim()));
		
	}
	
	protected void writeText(INodoXMl node, int nivel, StringBuilder sb)
	{
		writeTextSimple(node, nivel, sb);
		sb.append("\n");
	}
	
	public static String quitarInicio(String text)
	{
		while(text.startsWith("\t"))
		{
			text= text.substring(1);
		}
		
		while(text.startsWith(" "))
		{
			text= text.substring(1);
		}
		
		return text;
		
	}

	protected void writeHijo(INodoXMl node, int nivel, StringBuilder sb)
	{
		for (INodoXMl hijo : node.getHijos()) {
			sb.append(node2String(hijo, nivel + 1));
			sb.append("\n");
		}
	}

	protected StringBuilder writeAtributos(INodoXMl node, StringBuilder sb)
	{
		if (!node.getAtributos().isEmpty())
			sb.append(" ");
		for (Map.Entry<String, String> e : node.getAtributos().entrySet()) {
			sb.append((String) e.getKey() + "=\"" + (String) e.getValue() + "\"");
			sb.append(" ");
		}
		if (sb.charAt(sb.length() - 1) == ' ') {
			String s = sb.substring(0, sb.length() - 1).toString();
			sb = new StringBuilder(s);
		}
		return sb;
	}

	public String tabs(int num)
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < num; i++)
			sb.append("\t");
		return sb.toString();
	}
}
