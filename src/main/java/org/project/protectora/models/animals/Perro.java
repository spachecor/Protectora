package org.project.protectora.models.animals;

import org.project.protectora.properties.*;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Clase Perro, que define las propiedades y los comportamientos básicos de los objetos del tipo Perro
 * @see Animal
 * @author Selene
 * @version 1.0
 */
public final class Perro extends Animal{
    private final RazaPerro raza;
    private final Tamanio tamanio;
    /**
     * Constructor que nos permite fijar también la fecha de nacimiento del animal y su edad en meses pero no si el animal
     * está o no castrado
     * @param nombre el nombre del animal
     * @param color el color del animal
     * @param sexo el sexo del animal
     * @param fechaNacimiento la fecha de nacimiento del animal en meses(podría ser aproximada)
     * @param raza la raza del animal
     * @param tamanio el tamanio del animal
     */
    public Perro(String nombre, Color color, Sexo sexo, LocalDate fechaNacimiento, RazaPerro raza, Tamanio tamanio, byte[] img){
        super(nombre, color, sexo, fechaNacimiento, img);
        this.raza=raza;
        this.tamanio=tamanio;
    }
    /**
     * Constructor que nos permite fijar la fecha de nacimiento y la edad del animal y si está o no castrado
     * @param nombre el nombre del animal
     * @param color el color del animal
     * @param sexo el sexo del animal
     * @param fechaNacimiento la fecha de nacimiento del animal en meses(podría ser aproximada)
     * @param castrado si el animal está o no castrado
     * @param raza la raza del animal
     * @param tamanio el tamanio del animal
     */
    public Perro(String nombre, Color color, Sexo sexo, LocalDate fechaNacimiento, boolean castrado, RazaPerro raza, Tamanio tamanio, byte[] img){
        super(nombre, color, sexo, fechaNacimiento, castrado, img);
        this.raza=raza;
        this.tamanio=tamanio;
    }
    /**
     * Constructor completo que asigna todos los campos al animal
     * @param nombre el nombre del animal
     * @param color el color del animal
     * @param sexo el sexo del animal
     * @param fechaNacimiento la fecha de nacimiento del animal en meses(podría ser aproximada)
     * @param castrado si el animal está o no castrado
     * @param chip el nº de chip del animal de 15 cifras
     * @param raza la raza del animal
     * @param tamanio el tamanio del animal
     */
    public Perro(String nombre, Color color, Sexo sexo, LocalDate fechaNacimiento, boolean castrado, long chip, RazaPerro raza, Tamanio tamanio, byte[] img){
        super(nombre, color, sexo, fechaNacimiento, castrado, chip, img);
        this.raza=raza;
        this.tamanio=tamanio;
    }
    /**
     * Constructor usado para la creación de un gato ya creado anteriormente (p.ej restauracion de entidades creadas en
     * otras sesiones
     * @param nombre el nombre del animal
     * @param color el color del animal
     * @param sexo el sexo del animal
     * @param fechaNacimiento la fecha de nacimiento del animal en meses(podría ser aproximada)
     * @param fechaEntradaProtectora la fecha en la que el animal entró en la protectora
     * @param castrado si el animal está o no castrado
     * @param chip el nº de chip del animal de 15 cifras
     * @param raza la raza del gato
     * @param tamanio el tamaño del gato
     */
    public Perro(String nombre, Color color, Sexo sexo, LocalDate fechaNacimiento, LocalDate fechaEntradaProtectora, Boolean castrado, Long chip, RazaPerro raza, Tamanio tamanio, byte[] img) {
        super(nombre, color, sexo, fechaNacimiento, castrado, chip, img);
        super.setFechaEntradaProtectora(fechaEntradaProtectora);
        this.raza = raza;
        this.tamanio = tamanio;
    }
    /**
     * Constructor usado para la creación de un perro ya creado anteriormente (p.ej restauracion de entidades creadas en
     * otras sesiones
     * @param id el id del animal
     * @param nombre el nombre del animal
     * @param color el color del animal
     * @param sexo el sexo del animal
     * @param fechaNacimiento la fecha de nacimiento del animal en meses(podría ser aproximada)
     * @param fechaEntradaProtectora la fecha en la que el animal entró en la protectora
     * @param castrado si el animal está o no castrado
     * @param chip el nº de chip del animal de 15 cifras
     * @param raza la raza del perro
     * @param tamanio el tamaño del perro
     */
    public Perro(String id, String nombre, Color color, Sexo sexo, LocalDate fechaNacimiento, LocalDate fechaEntradaProtectora, Boolean castrado, Long chip, RazaPerro raza, Tamanio tamanio, byte[] img) {
        super(id, nombre, color, sexo, fechaNacimiento, fechaEntradaProtectora, castrado, chip, img);
        this.raza = raza;
        this.tamanio = tamanio;
    }

    @Override
    public String toString() {
        return "Perro{" +
                super.toString()+
                ", raza = " + raza +
                ", tamanio = " + tamanio+"}";
    }
    @Override
    public String getDescripcion() {
        return "ID: "+this.getId()+"\n"+
                "Nombre: "+this.getNombre()+"\n"+
                "Edad: "+((this.getEdad()>12)?this.getEdad()/12+" año/s":this.getEdad()+" mes/es")+"\n"+
                "Raza del perro: "+this.getRaza().getRaza().replace("-", " ")+"\n"+
                "Color: "+this.getColor().getColor().replace("-", " ");
    }
    @Override
    public String getDescripcionAmpliada(){
        return "- ID: "+this.getId()+"\n"+
                "- Nombre: "+this.getNombre()+"\n"+
                "- Color: "+this.getColor()+"\n"+
                "- Sexo: "+this.getSexo()+"\n"+
                "- Fecha de nacimiento: "+this.getFechaNacimiento()+"\n"+
                "- Edad: "+((this.getEdad()>12)?this.getEdad()/12+" año/s":this.getEdad()+" mes/es")+"\n"+
                "- Tiempo en la protectora: "+((this.getTiempoEnProtectora()>12)?this.getTiempoEnProtectora()/12+" año/s":this.getTiempoEnProtectora()+" mes/es")+"\n"+
                "- "+((getCastrado()==null)?"Animal castrado\n":"Animal sin castrar\n")+
                "- "+((getChip()==null)?"Animal sin chip\n":"Animal con chip\n")+
                "- Raza del perro: "+this.getRaza()+"\n"+
                "- Tamanio: "+this.getTamanio();
                //"- Estado: "+this.getEstado();
    }
    @Override
    public String getTipoAnimal() {
        return Perro.class.getName().substring(Perro.class.getName().lastIndexOf(".")+1);
    }

    public RazaPerro getRaza() {
        return raza;
    }

    public Tamanio getTamanio() {
        return tamanio;
    }
}
