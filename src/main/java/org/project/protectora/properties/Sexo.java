package org.project.protectora.properties;
/**
 * Clase que contiene los posibles sexos de los animales como constantes para utilidades
 * @author Selene
 * @version 1.0
 */
public enum Sexo {
    MACHO("macho"),
    HEMBRA("hembra");
    private final String SEXO;
    Sexo(String sexo){
        this.SEXO =sexo;
    }
    public String getSexo(){
        return this.SEXO;
    }
    /**
     * Método que sirve para retornar el objeto del tipo de la clase si se le mete la cadena que le corresponde
     * @param sexo el sexo del animal
     * @return el objeto del sexo del animal
     */
    public static Sexo dictionary(String sexo){
        if (sexo.equals("hembra")) {
            return Sexo.HEMBRA;
        }
        return Sexo.MACHO;
    }
}
