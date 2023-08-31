package java9.tostring;

import java.util.Map;

import xml.NodoXml;

public class Node2String
{
	public String node2String(NodoXml node)
	{
		return node2String(node, 0);
	}

	public String node2String(NodoXml node, int nivel)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(tabs(nivel));
		sb.append("<");
		sb.append(node.getTagname());
		if (!node.getAtributos().isEmpty())
			sb.append(" ");
		for (Map.Entry<String, String> e : node.getAtributos().entrySet()) {
			sb.append((String) e.getKey() + "=\"" + (String) e.getKey() + "\"");
			sb.append(" ");
		}
		if (sb.charAt(sb.length() - 1) == ' ') {
			String s = sb.substring(0, sb.length() - 1).toString();
			sb = new StringBuilder(s);
		}
		if (!node.getHijos().isEmpty() || (node.getText() != null && !node.getText().isEmpty())) {
			sb.append(">");
			sb.append("\n");
			if (!node.getHijos().isEmpty()) {
				for (NodoXml hijo : node.getHijos()) {
					sb.append(node2String(hijo, nivel + 1));
					sb.append("\n");
				}
			} else if (node.getText() != null || !node.getText().isEmpty()) {
				sb.append(tabs(nivel + 1));
				sb.append(node.getText());
				sb.append("\n");
			}
			sb.append(tabs(nivel));
			sb.append("</");
			sb.append(node.getTagname());
			sb.append(">");
		} else {
			sb.append("/>");
		}
		return sb.toString();
	}

	public String tabs(int num)
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < num; i++)
			sb.append("\t");
		return sb.toString();
	}
}
