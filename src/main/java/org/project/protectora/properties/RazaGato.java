package org.project.protectora.properties;

/**
 * Clase que contiene las razas de los gatos como constantes para utilidades
 * @author Selene
 * @version 1.1
 */
public enum RazaGato {
    PERSA("Persa"),
    ESFINGE("Esfinge"),
    SIAMES("Siames"),
    MAINE_COON("Maine-Coon"),
    BENGALI("Bengali"),
    RAGDOLL("Ragdoll"),
    SIBERIANO("Siberiano"),
    ANGORA("Angora"),
    CORNISH_REX("Cornish-Rex"),
    COMUN_EUROPEO("Común-europeo"),
    ORIENTAL("Oriental"),
    EXOTICO("Exótico"),
    SOMALI("Somalí"),
    CURL_AMERICANO("Curl-Americano"),
    AZUL_RUSO("Azul-Ruso"),
    ABISINIO("Abisinio"),
    CARTUJO("Cartujo"),
    BOMBAY("Bombay"),
    BOSQUE_NORUEGA("Bosque-de-Noruega");
    private final String RAZA;
    RazaGato(String raza){
        this.RAZA=raza;
    }
    public String getRaza() {
        return RAZA;
    }
    /**
     * Método que sirve para retornar el objeto del tipo de la clase si se le mete la cadena que le corresponde
     * @param raza la raza del gato
     * @return el objeto de la raza del gato
     */
    public static RazaGato dictionary(String raza){
        switch (raza){
            case "Persa" -> {
                return RazaGato.PERSA;
            }
            case "Esfinge" -> {
                return RazaGato.ESFINGE;
            }
            case "Siames" -> {
                return RazaGato.SIAMES;
            }
            case "Maine-Coon" -> {
                return RazaGato.MAINE_COON;
            }
            case "Bengali" -> {
                return RazaGato.BENGALI;
            }
            case "Ragdoll" -> {
                return RazaGato.RAGDOLL;
            }
            case "Siberiano" -> {
                return RazaGato.SIBERIANO;
            }
            case "Angora" -> {
                return RazaGato.ANGORA;
            }
            case "Cornish-Rex" -> {
                return RazaGato.CORNISH_REX;
            }
            case "Oriental" -> {
                return RazaGato.ORIENTAL;
            }
            case "Exótico" -> {
                return RazaGato.EXOTICO;
            }
            case "Somalí" -> {
                return RazaGato.SOMALI;
            }
            case "Curl-Americano" -> {
                return RazaGato.CURL_AMERICANO;
            }
            case "Azul-Ruso" -> {
                return RazaGato.AZUL_RUSO;
            }
            case "Abisinio" -> {
                return RazaGato.ABISINIO;
            }
            case "Cartujo" -> {
                return RazaGato.CARTUJO;
            }
            case "Bombay" -> {
                return RazaGato.BOMBAY;
            }
            case "Bosque-de-Noruega" -> {
                return RazaGato.BOSQUE_NORUEGA;
            }
            default -> {
                return RazaGato.COMUN_EUROPEO;
            }
        }
    }
}
