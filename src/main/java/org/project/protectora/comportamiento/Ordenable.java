package org.project.protectora.comportamiento;

import org.project.protectora.models.Entidad;
import org.project.protectora.properties.CamposOrdenacion;
import org.project.protectora.properties.DireccionOrdenacion;

import java.util.List;

/**
 * Interfaz que define el comportamiento de ordenación de la lista.
 * @param <T> un tipo de objeto genérico que herede de la clase Entidad
 * @author Selene
 * @version 1.0
 */
public interface Ordenable<T extends Entidad> {
    /**
     * Método introduce en una lista tipo List la lista en cuestión pero ordenada
     * @param campo el campo por el que va a ordenar
     * @param direccion la direccion en la que va a ordenar, puede ser ascendente o descendente
     * @return Devuelve una lista pero ordenada
     */
    List<T> listar(CamposOrdenacion campo, DireccionOrdenacion direccion);
}
