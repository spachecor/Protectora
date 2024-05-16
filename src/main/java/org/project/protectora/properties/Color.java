package org.project.protectora.properties;

/**
 * Clase que contiene los colores del manto de los animales como constantes para utilidades
 * @author Selene
 * @version 1.0
 */
public enum Color {
    MARRON_OSCURO("marrón-oscuro"),
    MARRON_CLARO("marrón-claro"),
    BLANCO("blanco"),
    NEGRO("negro"),
    BICOLOR("bicolor"),
    TRICOLOR("tricolor"),
    NARANJA("naranja"),
    AZUL("azul");
    private final String COLOR;
    Color(String color){
        this.COLOR =color;
    }
    public String getColor(){
        return this.COLOR;
    }
    public static Color dictionary(String color){
        switch (color){
            case "marrón-oscuro" -> {
                return Color.MARRON_OSCURO;
            }
            case "marrón-claro" -> {
                return Color.MARRON_CLARO;
            }
            case "blanco" -> {
                return Color.BLANCO;
            }
            case "negro" -> {
                return Color.NEGRO;
            }
            case "bicolor" -> {
                return Color.BICOLOR;
            }
            case "tricolor" -> {
                return Color.TRICOLOR;
            }
            case "naranja" -> {
                return Color.NARANJA;
            }
            default -> {
                return Color.AZUL;
            }
        }
    }
}
