package org.project.protectora.models.adopcion;

import org.project.protectora.models.Entidad;
import org.project.protectora.models.animals.Animal;
import org.project.protectora.models.personas.Usuario;
import org.project.protectora.properties.*;
import org.project.protectora.servicios.bbdd.ConexionBBDD;

import java.util.ArrayList;

/**
 * Clase que define las propiedades y el comportamiento del objeto de tipo SolicitudAdopcion. Éste controla cuando un usuario
 * solicita la adopción de un animal, los diferentes estados de esta solicitud, así como todop lo necesario sobre el procedimiento
 * @author Selene
 * @version 1.0
 */
public final class SolicitudAdopcion extends Entidad{
    private static int contador;
    private Animal animal;
    private Usuario adoptante;
    private ArrayList<EstadoAdopcion> estados;
    static{
        SolicitudAdopcion.contador=1;
    }
    /**
     * Constructor del objeto del tipo SolicitudAdopcion
     * @param animal el animal a adoptar
     * @param adoptante el usuario que quiere adoptar al animal
     */
    public SolicitudAdopcion(Animal animal, Usuario adoptante) {
        super(SolicitudAdopcion.class.getName(), SolicitudAdopcion.contador);
        this.estados=new ArrayList<>();
        this.animal=animal;
        this.adoptante=adoptante;
        this.asignarEstadoAdopcion(animal);
        SolicitudAdopcion.contador++;
    }

    /**
     * Constructor usado para la creación de una solicitud ya creada anteriormente (p.ej restauracion de entidades creadas en
     * otras sesiones
     * @param id el id de la solicitud
     * @param animal el animal a adoptar
     * @param adoptante el usuario interesado en la adopcion
     */
    public SolicitudAdopcion(String id, Animal animal, Usuario adoptante){
        super(id);
        this.animal = animal;
        this.adoptante = adoptante;
    }
    public static EstadoAdopcion resolverAdopcion(SolicitudAdopcion solicitud, boolean aprobacion){
        if(aprobacion&&solicitud.getEstado()==EstadoAdopcion.EN_ESPERA){
            solicitud.setEstado(EstadoAdopcion.APROBADA);
        }else solicitud.setEstado(EstadoAdopcion.DENEGADA);
        return solicitud.getEstado();
    }
    /**
     * Método que asigna el estado de la adopcion inicial a la solicitud según el estado del animal, dejando solo adoptar
     * a animales adoptables. Pone el estado es espera si es adoptable o denegada si no es adoptable
     * @param animal el animal que se quiere adoptar
     */
    public void asignarEstadoAdopcion(Animal animal){
        if(animal.getEstado()== EstadoAnimal.INDOCUMENTADO
                ||animal.getEstado()==EstadoAnimal.ADOPTADO
                ||animal.getEstado()==EstadoAnimal.FALLECIDO)this.setEstado(EstadoAdopcion.DENEGADA);
        else setEstado(EstadoAdopcion.EN_ESPERA);
    }

    @Override
    public String toString() {
        return "SolicitudAdopcion{" +
                "animal=" + animal +
                ", adoptante=" + adoptante /*+
                ", estado=" + this.estados.getLast() +
                '}'*/;
    }

    @Override
    public String getDescripcion() {
        return "Id: "+this.getId()+"\n"+
                "animal con id: "+this.getAnimal().getId()+"\n"+
                "adoptante con id: "+this.getAdoptante().getId()/*+"\n"+
                "estado: "+this.getEstado()*/;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Usuario getAdoptante() {
        return adoptante;
    }

    public void setAdoptante(Usuario adoptante) {
        this.adoptante = adoptante;
    }

    public EstadoAdopcion getEstado() {
        return this.estados.getLast();
    }

    public void setEstado(EstadoAdopcion estado) {
        this.estados.add(estado);
    }

    public ArrayList<EstadoAdopcion> getEstados() {
        return estados;
    }

    public void setEstados(ArrayList<EstadoAdopcion> estados) {
        this.estados = estados;
    }
    public static int getContador(){
        return SolicitudAdopcion.contador-1;
    }
    public static void setContador(int contador){
        SolicitudAdopcion.contador=contador+1;
    }
    public static void reestablecerContador(){
        try{
            ConexionBBDD conexionBBDD = new ConexionBBDD();
            setContador(conexionBBDD.contarsolicitudes());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
