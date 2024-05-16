package org.project.protectora.properties;

import java.time.LocalDateTime;

/**
 * Clase que contiene los estados de las solicitudes de adopción de los animales como constantes para utilidades
 * @author Selene
 * @version 1.0
 */
public enum EstadoAdopcion {
    DENEGADA("denegada", LocalDateTime.now()),
    EN_ESPERA("en-espera", LocalDateTime.now()),
    APROBADA("aprobada", LocalDateTime.now());
    private final String ESTADO;
    private final LocalDateTime CAMBIO_ESTADO;
    EstadoAdopcion(String estado, LocalDateTime cambioEstado){
        this.ESTADO =estado;
        this.CAMBIO_ESTADO=cambioEstado;
    }

    public String getEstado() {
        return ESTADO;
    }
    public LocalDateTime getCambioEstado(){
        return CAMBIO_ESTADO;
    }
    /**
     * Método que sirve para retornar el objeto del tipo de la clase si se le mete la cadena que le corresponde
     * @param estado el estado de la solicitud de adopción
     * @return el objeto del estado de la solicitud de adopción
     */
    public static EstadoAdopcion dictionary(String estado){
        switch (estado) {
            case "en-espera" -> {
                return EstadoAdopcion.EN_ESPERA;
            }
            case "aprobada" -> {
                return EstadoAdopcion.APROBADA;
            }
            default -> {
                return EstadoAdopcion.DENEGADA;
            }
        }
    }
}
