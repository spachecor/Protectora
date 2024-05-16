package org.project.protectora.properties;
/**
 * Clase que contiene los posibles tamaños de los animales como constantes para utilidades
 * @author Selene
 * @version 1.0
 */
public enum Tamanio {
    PEQUENIO("pequeño"),
    MEDIANO("mediano"),
    GRANDE("grande"),
    GIGANTE("gigante");
    private final String TAMANIO;
    Tamanio(String tamanio){
        this.TAMANIO =tamanio;
    }
    public String getTamanio(){
        return this.TAMANIO;
    }
    /**
     * Método que sirve para retornar el objeto del tipo de la clase si se le mete la cadena que le corresponde
     * @param tamanio el tamanio del animal
     * @return el objeto del tamanio del animal
     */
    public static Tamanio dictionary(String tamanio){
        switch (tamanio){
            case "pequeño" -> {
                return Tamanio.PEQUENIO;
            }
            case "grande" -> {
                return Tamanio.GRANDE;
            }
            case "gigante" -> {
                return Tamanio.GIGANTE;
            }default -> {
                return Tamanio.MEDIANO;
            }
        }
    }
}
