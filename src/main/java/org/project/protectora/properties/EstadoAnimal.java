package org.project.protectora.properties;

import java.time.LocalDateTime;

/**
 * Clase que contiene los estados que pueden tener los animales como constantes para utilidades
 * @author Selene
 * @version 1.0
 */
public enum EstadoAnimal {
    ADOPTABLE("adoptable", LocalDateTime.now()),
    ADOPTADO("adoptado", LocalDateTime.now()),
    INDOCUMENTADO("indocumentado", LocalDateTime.now()),
    FALLECIDO("fallecido", LocalDateTime.now());
    private final String ESTADO;
    private final LocalDateTime CAMBIO_ESTADO;
    EstadoAnimal(String estado, LocalDateTime cambioEstado){
        this.ESTADO =estado;
        this.CAMBIO_ESTADO =cambioEstado;
    }
    /**
     * Método que sirve para retornar el objeto del tipo de la clase si se le mete la cadena que le corresponde
     * @param estado el estado del animal
     * @return el objeto del estado del animal
     */
    public static EstadoAnimal dictionary(String estado){
        switch (estado){
            case "adoptable" -> {
                return ADOPTABLE;
            }
            case "adoptado" -> {
                return ADOPTADO;
            }
            case "fallecido" -> {
                return FALLECIDO;
            }
            default -> {
                return INDOCUMENTADO;
            }
        }
    }
    public String getEstado(){
        return this.ESTADO;
    }
    public LocalDateTime getCambioEstado(){
        return this.CAMBIO_ESTADO;
    }
}
