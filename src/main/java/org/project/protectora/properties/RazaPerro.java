package org.project.protectora.properties;
/**
 * Clase que contiene las razas de los perros como constantes para utilidades
 * @author Selene
 * @version 1.0
 */
public enum RazaPerro {
    CHIHUAHUA("Chihuahua"),
    POMERANIA("Pomerania"),
    BICHON_MALTES("Bichón-Maltés"),
    CANICHE("Caniche"),
    BULLDOG_FRANCES("Bulldog-Francés"),
    YORKSHIRE("Yorkshire"),
    BEAGLE("Beagle"),
    BOXER("Bóxer"),
    JACK_RUSSELL("Jack-Russell"),
    BORDER_COLLIE("Border-Collie"),
    BULL_TERRIER("Bull-Terrier"),
    CARLINO("Carlino"),
    MESTIZO("Mestizo"),
    GALGO("Galgo"),
    HUSKY_SIBERIANO("Husky-Siberiano"),
    SHIBA_INU("Shiba-Inu"),
    DALMATA("Dálmata"),
    SHAR_PEI("Shar-Pei"),
    LABRADOR("Labrador"),
    SAN_BERNARDO("San-Bernardo"),
    ROTTWEILER("Rottweiler"),
    BOBTAIL("Bobtail"),
    DOGO_ARGENTINO("Dogo-Argentino"),
    GRAN_DANES("Gran-Danés");
    private final String RAZA;
    RazaPerro(String raza){
        this.RAZA=raza;
    }
    public String getRaza(){
        return this.RAZA;
    }
    /**
     * Método que sirve para retornar el objeto del tipo de la clase si se le mete la cadena que le corresponde
     * @param raza la raza del perro
     * @return el objeto de la raza de perro
     */
    public static RazaPerro dictionary(String raza){
        switch (raza){
            case "Chihuahua" -> {
                return RazaPerro.CHIHUAHUA;
            }
            case "Pomerania" -> {
                return RazaPerro.POMERANIA;
            }
            case "Bichón-Maltés" -> {
                return RazaPerro.BICHON_MALTES;
            }
            case "Caniche" -> {
                return RazaPerro.CANICHE;
            }
            case "Bulldog-Francés" -> {
                return RazaPerro.BULLDOG_FRANCES;
            }
            case "Yorkshire" -> {
                return RazaPerro.YORKSHIRE;
            }case "Beagle" -> {
                return RazaPerro.BEAGLE;
            }
            case "Bóxer" -> {
                return RazaPerro.BOXER;
            }
            case "Jack-Russell" -> {
                return RazaPerro.JACK_RUSSELL;
            }
            case "Border-Collie" -> {
                return RazaPerro.BORDER_COLLIE;
            }
            case "Bull-Terrier" -> {
                return RazaPerro.BULL_TERRIER;
            }
            case "Carlino" -> {
                return RazaPerro.CARLINO;
            }
            case "Galgo" -> {
                return RazaPerro.GALGO;
            }
            case "Husky-Siberiano" -> {
                return RazaPerro.HUSKY_SIBERIANO;
            }
            case "Shiba-Inu" -> {
                return RazaPerro.SHIBA_INU;
            }
            case "Dálmata" -> {
                return RazaPerro.DALMATA;
            }
            case "Shar-Pei" -> {
                return RazaPerro.SHAR_PEI;
            }
            case "Labrador" -> {
                return RazaPerro.LABRADOR;
            }
            case "San-Bernardo" -> {
                return RazaPerro.SAN_BERNARDO;
            }
            case "Rottweiler" -> {
                return RazaPerro.ROTTWEILER;
            }
            case "Bobtail" -> {
                return RazaPerro.BOBTAIL;
            }
            case "Dogo-Argentino" -> {
                return RazaPerro.DOGO_ARGENTINO;
            }
            case "Gran-Danes" -> {
                return RazaPerro.GRAN_DANES;
            }
            default -> {
                return RazaPerro.MESTIZO;
            }
        }
    }
}
