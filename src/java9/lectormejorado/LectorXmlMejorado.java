package java9.lectormejorado;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;

import xml.ConstructorArbolXML;
import xml.DividirStrings;
import xml.Nodo2String;
import xml.NodoXml;
import xml.XMLStructure;

public class LectorXmlMejorado {
  protected static final String INICIO_COMENTARIO_XML = "<!--";
  
  protected static final String FIN_COMENTARIO_XML = "-->";
  
  protected static final String ABRIR_ETIQUETA = "<";
  
  NodoXml nodeActual;
  
  NodoXml nodePadre;
  
  Stack<NodoXml> etiquetas;
  
  String lineaActual = "";
  
  boolean mostrar = false;
  
  protected boolean mostrarTokens = false;
  
  protected boolean esComentario;
  
  protected boolean mostrarComentario = false;
  
  public XMLStructure crear(String nombreFichero) throws IOException {
    XMLStructure str = new XMLStructure();
    this.etiquetas = new Stack<>();
    this.nodePadre = new NodoXml();
    str.setRaiz(this.nodePadre);
    this.etiquetas.push(this.nodePadre);
    Path path = Paths.get(nombreFichero, new String[0]);
    Stream<String> stream = Files.lines(path);
    stream.forEach(s -> {
          try {
            parseaLinea(s, 0);
          } catch (Exception e) {
            System.err.println("Error con linea " + s);
            e.printStackTrace();
          } 
        });
    stream.close();
    NodoXml raiz = str.getRaiz();
    if (raiz.numHijos() == 1 && raiz.getTagname() == null)
      str.setRaiz(raiz.getHijos().get(0)); 
    return str;
  }
  
  protected void parseaLinea(String s, int i) {
    if (this.esComentario)
      if (s.contains("-->")) {
        parseaLinea(quitarComentarioDeLinea(s, false), 0);
      } else {
        return;
      }  
    if (i > 100) {
      System.err.println("sobre pasando limite repeticion");
      return;
    } 
    if (s.startsWith("<?"))
      return; 
    this.lineaActual = this.lineaActual + " " + this.lineaActual;
    this.lineaActual = this.lineaActual.trim();
    if (this.mostrar)
      System.out.println("linea conjunta" + this.lineaActual + "\n  linea1  " + s + "\n"); 
    if (this.lineaActual.isEmpty())
      return; 
    if (this.lineaActual.contains("<!--")) {
      this.esComentario = true;
      String lineaAntesComent = quitarComentarioDeLinea(this.lineaActual, true);
      this.lineaActual = lineaAntesComent;
      return;
    } 
    if (this.lineaActual.contains("<") && this.lineaActual.contains(">")) {
      if (DividirStrings.numApariciones(this.lineaActual, "<") > 1) {
        String[] divs = this.lineaActual.split("<");
        if (this.mostrar) {
          System.out.println("unido" + this.lineaActual);
          System.out.println("divdido");
        } 
        this.lineaActual = "";
        for (String div : divs) {
          if (!div.trim().isEmpty()) {
            if (this.mostrar)
              System.out.println("<" + div); 
            parseaLinea("<" + div, i + 1);
          } 
        } 
      } else if (this.lineaActual.startsWith("<")) {
        if (this.lineaActual.endsWith(">")) {
          if (this.lineaActual.startsWith("</")) {
            NodoXml nodocerrado;
            if (this.mostrar)
              System.out.println("Cerrando " + this.lineaActual); 
            String[] tokens = this.lineaActual.split(" ");
            String tagName = tokens[0];
            tagName = tagName.substring(2, tagName.length() - 1);
            try {
              nodocerrado = this.etiquetas.pop();
            } catch (EmptyStackException e) {
              System.err.println("Intentando sacar nodo para cerrar " + tagName);
              throw e;
            } 
            if (tagName.equals(nodocerrado.getTagname())) {
              this.nodePadre = nodocerrado.getPadre();
              this.lineaActual = "";
            } else {
              System.err.println("mal cerrado " + tagName + " <->" + nodocerrado.getTagname());
            } 
          } else {
            NodoXml nodeNuevo = parseaLineaNodo(this.lineaActual);
            if (this.mostrar)
              System.out.println("hijo " + Nodo2String.node2String(nodeNuevo)); 
            this.nodePadre.addHijo(nodeNuevo);
            if (this.lineaActual.endsWith("/>")) {
              this.nodeActual = nodeNuevo;
              this.lineaActual = "";
            } else if (this.lineaActual.endsWith(">")) {
              this.nodeActual = nodeNuevo;
              this.nodePadre = nodeNuevo;
              this.lineaActual = "";
              this.etiquetas.push(this.nodeActual);
            } 
          } 
        } else {
          String[] divs = DividirStrings.divideSegunAngulo1(this.lineaActual, false);
          if (this.mostrar)
            System.out.println("divide por angulos"); 
          this.lineaActual = "";
          for (String div : divs) {
            parseaLinea(div, i + 1);
            if (this.mostrar)
              System.out.println(div); 
          } 
        } 
      } else {
        String[] divs = divideSegunAngulo(this.lineaActual);
        this.lineaActual = "";
        for (String div : divs)
          parseaLinea(div, i + 1); 
      } 
    } else if (!this.lineaActual.contains("<")) {
      if (this.mostrar)
        System.out.println("value " + this.lineaActual); 
      String text = this.nodeActual.getText();
      if (text == null)
        text = ""; 
      text = text + text;
      this.lineaActual = "";
      this.nodeActual.setText(text);
    } 
  }
  
  protected NodoXml parseaLineaNodo(String l) {
    if (l.startsWith("<") && l.endsWith(">")) {
      NodoXml nodeNuevo = new NodoXml();
      l = l.replace("\t", " ");
      while (l.contains("  "))
        l = l.replace("  ", " "); 
      String[] tokens = l.split(" ");
      if (this.mostrarTokens)
        System.out.println("tok" + Arrays.toString((Object[])tokens)); 
      String tagName = tokens[0];
      tagName = tagName.substring(1, tagName.length());
      if (tagName.endsWith(">"))
        tagName = tagName.substring(0, tagName.length() - 1); 
      nodeNuevo.setTagname(tagName);
      if (l.startsWith("</")) {
        if (tagName.startsWith("/"))
          tagName = tagName.substring(1, tagName.length()); 
        NodoXml nodocerrado = this.etiquetas.pop();
        if (tagName.equals(nodocerrado.getTagname())) {
          this.nodePadre = nodocerrado;
        } else {
          System.err.println("mal cerrado " + tagName + " <->" + nodocerrado.getTagname());
        } 
        return null;
      } 
      for (int i = 1; i < tokens.length; i++) {
        String token = tokens[i];
        if (this.mostrarTokens)
          System.out.println(token); 
        String nombreAtrib = null;
        String valorAtrib = null;
        if (token.contains("=")) {
          String[] subTokens = token.split("=");
          if (subTokens.length == 2) {
            nombreAtrib = subTokens[0];
            if (subTokens[1].contains("\""))
              subTokens[1] = subTokens[1].replace("\"", ""); 
            if (subTokens[1].contains("/>"))
              subTokens[1] = subTokens[1].replace("/>", ""); 
            if (subTokens[1].contains(">"))
              subTokens[1] = subTokens[1].replace(">", ""); 
            valorAtrib = subTokens[1];
          } 
        } else if (!"/>".equals(token)) {
          nombreAtrib = token;
        } 
        nodeNuevo.addAtributo(nombreAtrib, valorAtrib);
      } 
      return nodeNuevo;
    } 
    return null;
  }
  
  public static String[] divideSegunAngulo(String s) {
    int posAbrir = s.indexOf("<");
    int posCierreTotal = s.indexOf(">");
    String s0 = s.substring(0, posAbrir);
    String s1 = s.substring(posAbrir, posCierreTotal);
    String s2 = s.substring(posCierreTotal);
    System.out.println(s0);
    System.out.println(s1);
    System.out.println(s2);
    return new String[] { s0, s1, s2 };
  }
  
  public static String[] dividePor(String[] s, String[] c) {
    return dividePor(s, c, 0);
  }
  
  public static String[] dividePor(String[] s, String[] c, int posC) {
    if (posC == c.length)
      return s; 
    List<String> tokens = new ArrayList<>();
    for (String s1 : s) {
      String[] tokens1 = dividePor(s1.split(c[posC]), c, posC + 1);
      for (String tokenNuevo : tokens1)
        tokens.add(tokenNuevo); 
    } 
    return tokens.<String>toArray(new String[tokens.size()]);
  }
  
  protected String quitarComentarioDeLinea(String linea, boolean debeContenerInicioCOmentario) {
    if (this.mostrarComentario)
      System.out.println("linea con comentario " + linea); 
    int posComentario = linea.indexOf("<!--");
    if (posComentario == -1)
      if (!debeContenerInicioCOmentario && linea.contains("-->")) {
        posComentario = 0;
      } else {
        System.err.println("No contiene parte de comentario " + linea);
        return linea;
      }  
    String lineaAntesComent = linea.substring(0, posComentario);
    if (this.mostrarComentario)
      System.out.println(lineaAntesComent); 
    if (linea.contains("-->")) {
      if (this.mostrarComentario)
        System.out.println("En la propia linea finaliza el comentario"); 
      int posFinComentario = linea.indexOf("-->");
      this.esComentario = false;
      String lineaDespuesComent = linea.substring(posFinComentario + "-->".length());
      if (this.mostrarComentario)
        System.out.println(lineaDespuesComent); 
      lineaAntesComent = lineaAntesComent + lineaAntesComent;
    } else {
      System.out.println("En la propia linea  NO finaliza el comentario");
    } 
    if (this.mostrarComentario)
      System.out.println("linea sin comentario  " + lineaAntesComent); 
    return lineaAntesComent;
  }
  
  /**
  
  public static void main(String[] args) throws IOException {
    ConstructorArbolXML c = new ConstructorArbolXML();
    c.quitarComentarioDeLinea("<hola> <!-- hola --> hola", true);
    c.quitarComentarioDeLinea("<hola>--> hola", false);
    String nombreFichero = "C:/Users/xe76208/Desktop/IDE_APX_win64/IDE_APX_win64/workspace/PRUET00001-ES/src/main/resources/PRUET000-01-ES.xml";
    XMLStructure xml = c.crear(nombreFichero);
    System.out.println("Entero");
    System.out.println(Nodo2String.node2String(xml.getRaiz()));
  }
  
  */
  
  public boolean isMostrar() {
    return this.mostrar;
  }
  
  public void setMostrar(boolean mostrar) {
    this.mostrar = mostrar;
  }
}

