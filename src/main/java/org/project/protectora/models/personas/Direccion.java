package org.project.protectora.models.personas;

import org.eclipse.jdt.annotation.NonNull;

import java.io.Serial;
import java.io.Serializable;

/**
 * Clase que define las propiedades y el comportamiento del objeto de tipo Direccion, que es la dirección del usuario
 * @author Selene
 * @version 1.0
 */
public class Direccion implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String direccion, localidad, provincia;
    private Integer codigoPostal;

    /**
     * Constructor completo del objeto del tipo Dirección
     * @param direccion la dirección dentro de la localidad
     * @param direccionFacturacion la dirección de facturación, puede ser la misma que la dirección
     * @param localidad la localidad a la que pertenece la dirección
     * @param provincia la provincia a la que pertenece la dirección
     * @param codigoPostal el código postal al que pertenece la dirección
     */
    public Direccion(@NonNull String direccion,@NonNull String localidad,
                     @NonNull String provincia, @NonNull Integer codigoPostal){
        this.direccion=direccion;
        this.localidad=localidad;
        this.provincia=provincia;
        this.codigoPostal=codigoPostal;
    }

    @Override
    public String toString() {
        return "direccion='" + direccion + '\'' +
                ", localidad='" + localidad + '\'' +
                ", provincia='" + provincia + '\'' +
                ", codigoPostal=" + codigoPostal;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public Integer getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
}
