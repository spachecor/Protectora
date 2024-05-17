package org.project.protectora.models.personas;

import org.project.protectora.models.Entidad;
import org.eclipse.jdt.annotation.NonNull;
import org.project.protectora.servicios.bbdd.ConexionBBDD;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Clase Usuario(antes denominada Persona) que define las propiedades y el comportamiento del usuario
 * @author Selene
 * @version 1.4
 */
public class Usuario extends Entidad{
    private static Integer contador;
    private String dni, ocupacion, email, nombre;
    private Integer telefono;
    private Direccion direccion;
    private LocalDate fechaNacimiento;
    static{
        Usuario.contador=1;
    }
    /**
     * Constructor básico para crear un nuevo usuario con los mínimos datos
     * @param email el email del usuario
     * @param telefono el teléfono del usuario
     * @param fechaNacimiento la fecha de nacimiento del usuario
     */
    public Usuario(@NonNull String email,@NonNull Integer telefono,@NonNull String nombre, @NonNull LocalDate fechaNacimiento){
        super(Usuario.class.getName(), Usuario.contador);
        this.email=email;
        this.telefono=telefono;
        this.nombre=nombre;
        this.fechaNacimiento=fechaNacimiento;
        Usuario.contador++;
    }

    /**
     * Constructor completo para crear usuarios que no hayan sido creados antes justo en el momento de realizar la adopcion/compra
     * @param dni el dni del usuario
     * @param ocupacion la ocupacion del usuario
     * @param email el email del usuario
     * @param direccion la direccion del usuario
     * @param localidad la localidad del usuario
     * @param provincia la provincia del usuario
     * @param telefono el teléfono del usuario
     * @param codigoPostal el codigo postal del usuario
     * @param fechaNacimiento la fecha de nacimiento del usuario
     */
    public Usuario(String email,Integer telefono, String nombre, LocalDate fechaNacimiento,
                   @NonNull String dni, String ocupacion, String direccion,String localidad,
                   String provincia, Integer codigoPostal){
        this(email, telefono, nombre, fechaNacimiento);
        this.dni=dni;
        this.ocupacion=ocupacion;
        this.direccion=new Direccion(direccion, localidad, provincia, codigoPostal);
    }

    /**
     * Constructor usado para la creación de un usuario ya creado anteriormente (p.ej restauracion de entidades creadas en
     * otras sesiones
     * @param id el id del usuario
     * @param email el email de usuario
     * @param telefono el telefono del usuario
     * @param nombre el nombre completo del usuario
     * @param fechaNacimiento la fecha de nacimiento del usuario
     * @param dni el dni del usuario
     * @param ocupacion la ocupación del usuario
     * @param direccion la direccion del usuario (direccion simple, sin indicar ciudad o cp)
     * @param localidad la localidad de la direccion del usuario
     * @param provincia la provincia de la direccion del usuario
     * @param codigoPostal el codigo postal de usuario
     */
    public Usuario(String id, String email, Integer telefono, String nombre, LocalDate fechaNacimiento,
                   String dni, String ocupacion, String direccion, String localidad,
                   String provincia, Integer codigoPostal) {
        super(id);
        this.dni = dni;
        this.ocupacion = ocupacion;
        this.email = email;
        this.telefono = telefono;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion=new Direccion(direccion, localidad, provincia, codigoPostal);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                super.toString()+
                ", dni = '" + dni + '\'' +
                ", nombre = '" + nombre + '\'' +
                ", ocupacion='" + ocupacion + '\'' +
                ", email = '" + email + '\'' +
                ", direccion = '" + direccion + '\'' +
                ", telefono = " + telefono +
                ", fechaNacimiento = " + fechaNacimiento +
                ", edad = " + this.getEdad() + " años"+
                '}';
    }

    @Override
    public String getDescripcion() {
        return "Id: "+this.getId()+"\n"+
                "Dni: " + this.getDni() + "\n" +
                "Nombre: " + this.getNombre() + "\n" +
                "Ocupacion: " + this.getOcupacion() + "\n" +
                "Email: " + this.getEmail() + "\n" +
                "Direccion: " + this.getDireccion().getDireccion() + "\n" +
                "Localidad: " + this.getDireccion().getLocalidad() + "\n" +
                "Provincia: " + this.getDireccion().getProvincia() + "\n" +
                "CodigoPostal: " + this.getDireccion().getCodigoPostal() + "\n" +
                "Telefono: " + this.getTelefono() + "\n" +
                "FechaNacimiento: " + this.getFechaNacimiento() + "\n" +
                "Edad: " + this.getEdad() + " años";
    }

    public static Integer getContador(){
        return Usuario.contador-1;
    }

    public static void setContador(Integer contador) {
        Usuario.contador = contador+1;
    }

    public static void reestablecerContador(){
        try{
            ConexionBBDD conexionBBDD = new ConexionBBDD();
            setContador(conexionBBDD.contarUsuarios());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * @return Devuelve la edad del usuario en años
     */
    public Integer getEdad(){
        if(fechaNacimiento==null)return null;
        return (int)ChronoUnit.YEARS.between(this.fechaNacimiento, LocalDate.now());
    }

    public String getDni() {
        return dni;
    }

    private void setDni(String dni) {
        this.dni = dni;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    private void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
}
