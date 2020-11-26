
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author igorr
 */
public class GestorXPATH {

    AbreFile ab = new AbreFile();
    String salida = "";
    String comando = "";
    Document doc = null;
    XPath xpath = null;

    public String getComando() {
        return comando;
    }

    public void setComando(String comando) {
        this.comando = comando;
    }

    public String getSalida() {
        return salida;
    }

    public void setSalida(String salida) {
        this.salida = salida;
    }

    public int abrirMiXml(File file) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            System.out.println(file.getName());
            doc = builder.parse(file);
            xpath = XPathFactory.newInstance().newXPath();
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    public int ejecutarXPATH() {
        //miPrincipal.getSolicitud()
        String datos_nodo[];
        try {

            XPathExpression expresion = xpath.compile(comando);
            System.out.println(expresion.toString());

            Object result = expresion.evaluate(doc, XPathConstants.NODESET);
            System.out.println(result);
            NodeList listanodos = (NodeList) result;
            if (comando.equals("/Libros/Libro")|| comando.equals("//Libro")) {
                for (int i = 0; i < listanodos.getLength(); i++) {
                    if (listanodos.item(i).getNodeType() == Node.ELEMENT_NODE) {
                        datos_nodo = procesarLibro(listanodos.item(i));

                        salida = salida + "\n" + "Publicado en: " + datos_nodo[0];
                        salida = salida + "\n" + "El tituo del libro es: " + datos_nodo[1];
                        salida = salida + "\n" + "El autor del Libro es: " + datos_nodo[2] + "\n";
                        salida = salida + "******************************************" + "\n";
                        salida = salida + "******************************************" + "\n";

                    }

            
                }
            } else {
                for (int i = 0; i < listanodos.getLength(); i++) {
                    salida = salida + "\n" + listanodos.item(i).getFirstChild().getNodeValue();
                }
            }
            return 0;
        } catch (Exception e) {
            return -1;
        }
    }

    private String[] procesarLibro(Node node) {
        String datosDelLibro[] = new String[3];
        Node temporal = null;
        int contador = 1;

        datosDelLibro[0] = node.getAttributes().item(0).getNodeValue();
        NodeList ListaDeNodos = node.getChildNodes();

        for (int i = 0; i < ListaDeNodos.getLength(); i++) {
            temporal = ListaDeNodos.item(i);
            if (temporal.getNodeType() == Node.ELEMENT_NODE) {
                datosDelLibro[contador] = temporal.getFirstChild().getNodeValue();
                contador++;
            }
        }

        return datosDelLibro;
    }

}
