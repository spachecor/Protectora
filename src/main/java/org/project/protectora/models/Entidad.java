package org.project.protectora.models;

import org.project.protectora.models.animals.Animal;
import org.project.protectora.models.personas.Usuario;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * Clase que es una plantilla para las entidades para ciertos objetos del programa
 * @see Animal
 * @see Usuario
 * @author Selene
 * @version 1.0
 */
public abstract class Entidad implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String id;

    /**
     * Constructor de serie
     */
    public Entidad(){}
    /**
     * Constructor del objeto del tipo Entidad
     * @param nombreClase el nombre del hijo que se crea
     * @param numeroId el número que llevará el id del objeto, que se regirá por un contador de objetos creados
     */
    protected Entidad(String nombreClase, Integer numeroId){
        this.setId(nombreClase, numeroId);
    }

    /**
     * Constructor usado para la creación de una entidad con un id ya creado (p.ej restauracion de entidades creadas en
     * otras sesiones
     * @param id el id de la entidad
     */
    protected Entidad(String id){
        this.id = id;
    }

    public abstract String getDescripcion();

    @Override
    public String toString() {
        return "id = '" + id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entidad entidad)) return false;
        return Objects.equals(id, entidad.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    /**
     * Se encarga de asignar el id según la clase del objeto entrante y su posición de creación
     * @param nombreClase el nombre del tipo de objeto que entra, que será la cadena en el id
     * @param numeroId el contador de objetos de esa clase, que será el nº en el id
     */
    private void setId(String nombreClase, Integer numeroId){
        this.id=nombreClase.substring(nombreClase.lastIndexOf(".")+1).substring(0,3).toLowerCase().concat("-").concat(String.valueOf(numeroId));
    }

    /**
     * Método para asignar el id reescrito para poder reasignar el id de algún objeto
     * @param id el id del objeto
     */
    public void setId(String id){
        this.id=id;
    }
    public String getId(){
        return this.id;
    }
}
