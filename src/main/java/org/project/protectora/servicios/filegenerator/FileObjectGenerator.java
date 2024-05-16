package org.project.protectora.servicios.filegenerator;

import org.project.protectora.models.Entidad;
import org.project.protectora.models.adopcion.SolicitudAdopcion;
import org.project.protectora.models.animals.Animal;
import org.project.protectora.models.personas.Usuario;
import org.project.protectora.servicios.logger.ProtectoraLogger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * Clase FileGeneratorFromObject que comprende la lógica necesaria para generar ficheros de los objetos de tipo Entidad creados
 * durante la ejecución del programa
 * @see Entidad
 * @author Selene
 * @version 1.0
 */
public class FileObjectGenerator {
    private static final String FICHERO_ENTIDADES = "listaEntidades.txt";
    private static final String FICHERO_CONTADORES = "listaContadores.txt";
    /**
     * Método que agrega un objeto nuevo tipo lista que entra por parámetro al fichero(definido por constantes) y guarda
     * los contadores de las diferentes entidades
     * @param t el objeto de tipo T, que serán listas de entidades
     * @param <T> El tipo del objeto entrante
     */
    public static <T extends List<Entidad>> void saveData(T t){
        try(ObjectOutputStream escritura = new ObjectOutputStream(new FileOutputStream(System.getProperty("user.dir")+"/files/"+ FileObjectGenerator.FICHERO_ENTIDADES))){
            escritura.writeObject(t);
        }catch (IOException e){
            ProtectoraLogger.getLogger(FileObjectGenerator.class.getName()).log(Level.INFO, "Fallo al guardar los datos");
        }
        try(ObjectOutputStream escritura = new ObjectOutputStream(new FileOutputStream(System.getProperty("user.dir")+"/files/"+ FileObjectGenerator.FICHERO_CONTADORES))){
            List<Integer> contadores = new ArrayList<>();
            contadores.add(SolicitudAdopcion.getContador());
            contadores.add(Animal.getContador());
            contadores.add(Usuario.getContador());
            escritura.writeObject(contadores);
        }catch (IOException e){
            ProtectoraLogger.getLogger(FileObjectGenerator.class.getName()).log(Level.INFO, "Fallo al guardar los datos");
        }
    }
    /**
     * Método que devuelve una lista con las entidades de la sesión anterior y ajusta los contadores de las diferentes entidades
     * @return una lista de entidades de la sesión anterior
     */
    public static List<Entidad> recoverData(){
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(System.getProperty("user.dir")+"/files/"+ FileObjectGenerator.FICHERO_CONTADORES))){
            List<Integer> contadores = (List<Integer>) in.readObject();
            SolicitudAdopcion.setContador(contadores.get(0));
            Animal.setContador(contadores.get(1));
            Usuario.setContador(contadores.get(2));
        }catch (IOException | ClassNotFoundException e){
            ProtectoraLogger.getLogger(FileObjectGenerator.class.getName()).log(Level.INFO, "Fallo al restaurar los datos");
        }
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(System.getProperty("user.dir")+"/files/"+ FileObjectGenerator.FICHERO_ENTIDADES))){
            return  new ArrayList((List<Entidad>)in.readObject());
        }catch (IOException | ClassNotFoundException e){
            ProtectoraLogger.getLogger(FileObjectGenerator.class.getName()).log(Level.INFO, "Fallo al restaurar los datos");
            return null;
        }
    }
}
