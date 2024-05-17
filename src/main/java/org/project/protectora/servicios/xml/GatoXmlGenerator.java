package org.project.protectora.servicios.xml;

import org.project.protectora.models.*;
import org.project.protectora.models.animals.Gato;
import org.project.protectora.properties.*;
import org.project.protectora.servicios.listapersonalizada.ListaPersonalizadaList;
import org.w3c.dom.Element;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class GatoXmlGenerator extends XmlGenerator{

    @Override
    public <T extends Entidad> void finalObjectGenerator(String pathname, String rootTagName, ListaPersonalizadaList<T> listaPersonalizadaList) {
        List<Element> elements = generator(pathname, rootTagName);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for(Element element:elements){
            if(element.getElementsByTagName("chip").item(0).getTextContent().isEmpty()){
                listaPersonalizadaList.add((T)new Gato(element.getElementsByTagName("nombre").item(0).getTextContent()
                        , Color.dictionary(element.getElementsByTagName("color").item(0).getTextContent())
                        , Sexo.dictionary(element.getElementsByTagName("sexo").item(0).getTextContent())
                        , LocalDate.parse(element.getElementsByTagName("fechaNacimiento").item(0).getTextContent(), dateTimeFormatter)
                        , Boolean.parseBoolean(element.getElementsByTagName("castrado").item(0).getTextContent())
                        , RazaGato.dictionary(element.getElementsByTagName("raza").item(0).getTextContent())
                        , Tamanio.dictionary(element.getElementsByTagName("tamanio").item(0).getTextContent())
                        , new byte[1024]));
            }else {
                listaPersonalizadaList.add((T)new Gato(element.getElementsByTagName("nombre").item(0).getTextContent()
                        , Color.dictionary(element.getElementsByTagName("color").item(0).getTextContent())
                        , Sexo.dictionary(element.getElementsByTagName("sexo").item(0).getTextContent())
                        , LocalDate.parse(element.getElementsByTagName("fechaNacimiento").item(0).getTextContent(), dateTimeFormatter)
                        , Boolean.parseBoolean(element.getElementsByTagName("castrado").item(0).getTextContent())
                        , Long.parseLong(element.getElementsByTagName("chip").item(0).getTextContent())
                        , RazaGato.dictionary(element.getElementsByTagName("raza").item(0).getTextContent())
                        , Tamanio.dictionary(element.getElementsByTagName("tamanio").item(0).getTextContent())
                        , new byte[1024]));
            }
        }
    }
}
