package org.project.protectora.models.animals;

import org.project.protectora.properties.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class Otro extends Animal {
    /**
     * Constructor que nos permite fijar también la fecha de nacimiento del animal y su edad en meses pero no si el animal
     * está o no castrado
     * @param nombre el nombre del animal
     * @param color el color del animal
     * @param sexo el sexo del animal
     * @param fechaNacimiento la fecha de nacimiento del animal en meses(podría ser aproximada)
     */
    public Otro(String nombre, Color color, Sexo sexo, LocalDate fechaNacimiento, byte[] img){
        super(nombre, color, sexo, fechaNacimiento, img);
    }
    /**
     * Constructor que nos permite fijar la fecha de nacimiento y la edad del animal y si está o no castrado
     * @param nombre el nombre del animal
     * @param color el color del animal
     * @param sexo el sexo del animal
     * @param fechaNacimiento la fecha de nacimiento del animal en meses(podría ser aproximada)
     * @param castrado si el animal está o no castrado
     */
    public Otro(String nombre, Color color, Sexo sexo, LocalDate fechaNacimiento, boolean castrado, byte[] img){
        super(nombre, color, sexo, fechaNacimiento, castrado, img);
    }
    /**
     * Constructor completo que asigna todos los campos al animal
     * @param nombre el nombre del animal
     * @param color el color del animal
     * @param sexo el sexo del animal
     * @param fechaNacimiento la fecha de nacimiento del animal en meses(podría ser aproximada)
     * @param castrado si el animal está o no castrado
     * @param chip el nº de chip del animal de 15 cifras
     */
    public Otro(String nombre, Color color, Sexo sexo, LocalDate fechaNacimiento, boolean castrado, long chip, byte[] img){
        super(nombre, color, sexo, fechaNacimiento, castrado, chip, img);
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
     */
    public Otro(String nombre, Color color, Sexo sexo, LocalDate fechaNacimiento, LocalDate fechaEntradaProtectora, Boolean castrado, Long chip, byte[] img) {
        super(nombre, color, sexo, fechaNacimiento, castrado, chip, img);
        super.setFechaEntradaProtectora(fechaEntradaProtectora);
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
     */
    public Otro(String id, String nombre, Color color, Sexo sexo, LocalDate fechaNacimiento, LocalDate fechaEntradaProtectora, Boolean castrado, Long chip, byte[] img) {
        super(id, nombre, color, sexo, fechaNacimiento, fechaEntradaProtectora, castrado, chip, img);
    }
    @Override
    public String getTipoAnimal() {
        return Otro.class.getName().substring(Otro.class.getName().lastIndexOf(".")+1);
    }

    @Override
    public String getDescripcionAmpliada() {
        return "ID: "+this.getId()+"\n"+
                "Nombre: "+this.getNombre()+"\n"+
                "Edad: "+((this.getEdad()>12)?this.getEdad()/12+" año/s":this.getEdad()+" mes/es")+"\n"+
                "Color: "+this.getColor()+"\n"+
                "Tipo: Otro";
    }

    @Override
    public String getDescripcion() {
        return "- ID: "+this.getId()+"\n"+
                "- Nombre: "+this.getNombre()+"\n"+
                "- Color: "+this.getColor()+"\n"+
                "- Sexo: "+this.getSexo()+"\n"+
                "- Fecha de nacimiento: "+this.getFechaNacimiento()+"\n"+
                "- Edad: "+((this.getEdad()>12)?this.getEdad()/12+" año/s":this.getEdad()+" mes/es")+"\n"+
                "- Tiempo en la protectora: "+((this.getTiempoEnProtectora()>12)?this.getTiempoEnProtectora()/12+" año/s":this.getTiempoEnProtectora()+" mes/es")+"\n"+
                "- "+((getCastrado())?"Animal castrado\n":"Animal sin castrar\n")+
                "- "+((getChip()==null)?"Animal sin chip\n":"Animal con chip\n")+
                //"- Estado: "+this.getEstado()+"\n"+
                "- Tipo: Otro";
    }
}
