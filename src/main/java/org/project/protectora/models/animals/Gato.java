package org.project.protectora.models.animals;

import org.project.protectora.properties.*;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Clase Gato, que define las propiedades y los comportamientos básicos de los objetos del tipo Gato
 * @see Animal
 * @author Selene
 * @version 1.0
 */
public final class Gato extends Animal {
    private final RazaGato raza;
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
    public Gato(String nombre, Color color, Sexo sexo, LocalDate fechaNacimiento, RazaGato raza, Tamanio tamanio){
        super(nombre, color, sexo, fechaNacimiento);
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
    public Gato(String nombre, Color color, Sexo sexo, LocalDate fechaNacimiento, boolean castrado, RazaGato raza, Tamanio tamanio){
        super(nombre, color, sexo, fechaNacimiento, castrado);
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
    public Gato(String nombre, Color color, Sexo sexo, LocalDate fechaNacimiento, boolean castrado, long chip, RazaGato raza, Tamanio tamanio){
        super(nombre, color, sexo, fechaNacimiento, castrado, chip);
        this.raza=raza;
        this.tamanio=tamanio;
    }

    /**
     * Constructor usado para la creación de un gato ya creado anteriormente (p.ej restauracion de entidades creadas en
     * otras sesiones
     * @param id el id del animal
     * @param nombre el nombre del animal
     * @param color el color del animal
     * @param sexo el sexo del animal
     * @param fechaNacimiento la fecha de nacimiento del animal en meses(podría ser aproximada)
     * @param fechaEntradaProtectora la fecha en la que el animal entró en la protectora
     * @param castrado si el animal está o no castrado
     * @param chip el nº de chip del animal de 15 cifras
     * @param estados los diferentes estados del animal
     * @param raza la raza del gato
     * @param tamanio el tamaño del gato
     */
    public Gato(String id, String nombre, Color color, Sexo sexo, LocalDate fechaNacimiento, LocalDate fechaEntradaProtectora, Boolean castrado, Long chip, ArrayList<EstadoAnimal> estados, RazaGato raza, Tamanio tamanio) {
        super(id, nombre, color, sexo, fechaNacimiento, fechaEntradaProtectora, castrado, chip, estados);
        this.raza = raza;
        this.tamanio = tamanio;
    }

    @Override
    public String toString() {
        return "Gato{" +
                super.toString()+
                ", raza = " + raza +
                ", tamanio = " + tamanio+"}";
    }

    @Override
    public String getTipoAnimal() {
        return Gato.class.getName().substring(Gato.class.getName().lastIndexOf(".")+1);
    }

    @Override
    public String getDescripcion() {
        return "ID: "+this.getId()+"\n"+
                "Nombre: "+this.getNombre()+"\n"+
                "Edad: "+((this.getEdad()>12)?this.getEdad()/12+" año/s":this.getEdad()+" mes/es")+"\n"+
                "Raza del gato: "+this.getRaza()+"\n"+
                "Color: "+this.getColor();
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
                "- "+((getCastrado())?"Animal castrado\n":"Animal sin castrar\n")+
                "- "+((getChip()==null)?"Animal sin chip\n":"Animal con chip\n")+
                "- Raza del gato: "+this.getRaza()+"\n"+
                "- Tamanio: "+this.getTamanio()+"\n"+
                "- Estado: "+this.getEstado();
    }

    public RazaGato getRaza() {
        return raza;
    }

    public Tamanio getTamanio() {
        return tamanio;
    }

}
