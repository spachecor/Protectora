package org.project.protectora.comportamiento;

import org.project.protectora.models.Entidad;

import java.util.List;

/**
 * Interfaz que define el comportamiento de paginación(segmentación de una parte a otra) de una lista.
 * @author Selene
 * @version 1.0
 */
public interface Paginable<T extends Entidad> {
    /**
     * Método que introduce en una lista del tipo List el contenido de la lista en cuestión pero desde un límite hasta otro límite
     * @param limit desde donde
     * @param offset hasta donde
     * @return devuelve un objeto del tipo list desde un punto hasta otro de una lista
     */
    List<T> listar(int limit, int offset);
}
