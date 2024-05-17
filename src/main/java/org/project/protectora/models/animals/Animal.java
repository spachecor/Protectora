package org.project.protectora.models.animals;

import org.project.protectora.models.Entidad;
import org.eclipse.jdt.annotation.NonNull;
import org.project.protectora.properties.Color;
import org.project.protectora.properties.EstadoAnimal;
import org.project.protectora.properties.Sexo;
import org.project.protectora.servicios.bbdd.ConexionBBDD;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

/**
 * Clase Animal, que define las propiedades y los comportamientos básicos de los objetos del tipo animal
 * @see Entidad
 * @see Gato
 * @see Perro
 * @author Selene
 * @version 1.4
 */
public abstract class Animal extends Entidad{
    private static Integer contador;
    private String nombre;
    private final Color color;
    private final Sexo sexo;
    private final LocalDate fechaNacimiento;
    private LocalDate fechaEntradaProtectora;
    private Boolean castrado;
    private Long chip;
    private ArrayList<EstadoAnimal> estados;
    private byte[] img;
    static{
        Animal.contador=1;
    }
    /**
     * Constructor básico que crea un animal
     * @param nombre el nombre del animal
     * @param color el color del animal
     * @param sexo el sexo del animal
     * @param fechaNacimiento la fecha de nacimiento del animal en meses(podría ser aproximada)
     */
    protected Animal(@NonNull String nombre, @NonNull Color color, @NonNull Sexo sexo, @NonNull LocalDate fechaNacimiento, byte[] img){
        super(Animal.class.getName(), Animal.contador);
        this.nombre=nombre;
        this.color=color;
        this.sexo=sexo;
        //se instancia el Arraylist de estados
        this.estados=new ArrayList<>();
        //se introduce el nuevo estado
        this.setEstado(EstadoAnimal.INDOCUMENTADO);
        this.fechaEntradaProtectora=LocalDate.now();
        this.fechaNacimiento=fechaNacimiento;
        this.img=img;
        Animal.contador++;
    }
    /**
     * Constructor que nos permite fijar la fecha de nacimiento y la edad del animal y si está o no castrado
     * @param nombre el nombre del animal
     * @param color el color del animal
     * @param sexo el sexo del animal
     * @param fechaNacimiento la fecha de nacimiento del animal en meses(podría ser aproximada)
     * @param castrado si el animal está o no castrado
     */
    protected Animal(String nombre, Color color, Sexo sexo, LocalDate fechaNacimiento, @NonNull Boolean castrado, byte[] img){
        this(nombre, color, sexo, fechaNacimiento, img);
        this.castrado=castrado;
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
    protected Animal(String nombre, Color color, Sexo sexo, LocalDate fechaNacimiento, Boolean castrado, Long chip, byte[] img){
        this(nombre, color, sexo, fechaNacimiento, castrado, img);
        this.chip=chip;
        //si la lista de estados no está vacía, se vaciará, porque si llamamos a este constructor el animal sí tiene chip
        if(!this.estados.isEmpty())this.estados.removeAll(this.estados);
        //ahora, con la lista de estados vacía, se agrega el estado del animal
        this.setEstado(EstadoAnimal.ADOPTABLE);
    }

    /**
     * Constructor usado para la creación de un animal ya creado anteriormente (p.ej restauracion de entidades creadas en
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
     */
    public Animal(String id, String nombre, Color color, Sexo sexo, LocalDate fechaNacimiento, LocalDate fechaEntradaProtectora, Boolean castrado, Long chip, ArrayList<EstadoAnimal> estados, byte[] img) {
        super(id);
        this.nombre = nombre;
        this.color = color;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaEntradaProtectora = fechaEntradaProtectora;
        this.castrado = castrado;
        this.chip = chip;
        this.estados = estados;
        this.img=img;
    }

    @Override
    public String toString() {
        return  super.toString()+
                ", nombre = '" + nombre + '\'' +
                ", edad = "+this.getEdad() + " meses"+
                ", tiempoEnProtectora = " + getTiempoEnProtectora() + " meses"+
                ", color = " + color.getColor() +
                ", sexo = " + sexo.getSexo() +
                ", estado = " + estados.getLast().getEstado() +
                ", estados = " + estados +
                ", fechaNacimiento= " + fechaNacimiento +
                ", fechaEntradaProtectora = " + fechaEntradaProtectora +
                ", castrado = " + castrado +
                ", chip = " + chip;
    }

    public abstract String getTipoAnimal();
    public abstract String getDescripcionAmpliada();

    public static void reestablecerContador(){
        try{
            ConexionBBDD conexionBBDD = new ConexionBBDD();
            setContador(conexionBBDD.contarAnimales());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Integer getContador(){
        return Animal.contador-1;
    }
    public static void setContador(Integer contador){
        Animal.contador=contador+1;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Color getColor() {
        return color;
    }
    public Sexo getSexo() {
        return sexo;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
    /**
     * @return La edad del animal el meses
     */
    public Integer getEdad() {
        if(fechaNacimiento==null)return null;
        return (int)ChronoUnit.MONTHS.between(fechaNacimiento, LocalDate.now());
    }

    public LocalDate getFechaEntradaProtectora() {
        return fechaEntradaProtectora;
    }

    public void setFechaEntradaProtectora(LocalDate fechaEntradaProtectora) {
        this.fechaEntradaProtectora = fechaEntradaProtectora;
    }

    /**
     * @return El tiempo que lleva el animal en la protectora en meses
     */
    public Integer getTiempoEnProtectora() {
        if(fechaEntradaProtectora==null)return null;
        return (int)ChronoUnit.MONTHS.between(this.getFechaEntradaProtectora(), LocalDate.now());
    }

    public Boolean getCastrado() {
        return castrado;
    }

    public void setCastrado(Boolean castrado) {
        this.castrado = castrado;
    }

    public Long getChip() {
        return chip;
    }

    public void setChip(Long chip) {
        this.chip = chip;
    }

    public EstadoAnimal getEstado() {
        return estados.getLast();
    }

    public void setEstado(EstadoAnimal estado) {
        this.estados.add(estado);
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }
}
