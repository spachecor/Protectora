package org.project.protectora.servicios.xml;

import org.project.protectora.models.Entidad;
import org.project.protectora.servicios.listapersonalizada.ListaPersonalizadaList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class XmlGenerator{
    //todo reformular, hacerlo lo más genérico posible o descartar
    /**
     * Método que toma la ruta del archivo xml a leer y el nombre de la etiqueta raíz que contiene el objeto
     * y que genera una lista de elementos tipo Element para posteriormente poderlos procesar y convertir en objetos
     * del tipo requerido
     * @param pathname la ruta del archivo xml a leer
     * @param rootTagName el nombre de la etiqueta que contiene el objeto que deseamos transformar
     * @return una lista de objetos tipo Element
     */
    public static List<Element> generator(String pathname, String rootTagName){
        List<Element> elements = new ArrayList<>();
        try{
            //1º Creamos una instancia de un DocumentBuilderFactory(factoria de creadores de documentos)
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            //2º Creamos un objeto del tipo DocumentBuilder(Creador de documentos) con la instancia anterior
            DocumentBuilder db = dbf.newDocumentBuilder();
            //3º Creamos el documento pasándole el archivo xml extero, que entra como argumento en el método
            Document document = db.parse(new File(pathname));
            //4º Obtenemos la lista de nodos que tiene el documento xml
            NodeList nodeList = document.getElementsByTagName(rootTagName);
            //5º Recooremos la lista de animales para ir sacando las características de cada objeto
            for(int i=0;i<nodeList.getLength();i++){
                //tomamos el item de la lista como un nodo para tratarlo/usarlo
                Node node = nodeList.item(i);
                //comprobamos que el nodo es de tipo element para poder aplicar el siguiente casting
                if(node.getNodeType()==Node.ELEMENT_NODE){
                    //tomamos el nodo como element usando casting
                    Element element = (Element) node;
                    //ahora agregamos este element a una lista de elementos, para poderlo pasar a otro método que tomará
                    //esta lista y la convertirá en objetos
                    elements.add(element);
                }
            }
        }catch (Exception e){
            System.out.println("Imposible cargar el documento xml");
            e.printStackTrace();
        }
        return elements;
    }

    public abstract <T extends Entidad> void finalObjectGenerator(String pathname, String rootTagName, ListaPersonalizadaList<T> listaPersonalizadaList);
}
