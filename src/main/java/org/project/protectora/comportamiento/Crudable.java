package org.project.protectora.comportamiento;

import org.project.protectora.models.Entidad;

import java.util.List;

/**
 * Interfaz que define el comportamiento CRUD: Create, Read, Update y Delete.
 * @param <T> un tipo de objeto genérico que herede de la clase Entidad
 * @author Selene
 * @version 1.0
 */
public interface Crudable<T extends Entidad> {
    /**
     * Método que busca en una lista un objeto por ID. Si no encuentra nada, devuelve null
     * @param id el id único de cada objeto
     * @return El objeto encontrado que coincide con el id
     */
    T searchForId(String id);

    /**
     * Método que aniade un objeto a una lista
     * @param t el objeto a aniadir
     */
    void add(T t);

    /**
     * Método que modifica un objeto. Para ello, se le pasará un nuevo objeto con los datos modificados pero el mismo id,
     * después se asignan los nuevos valores al antiguo.
     * El objeto t que modifica será un clon del objeto a modificar, que tendrá su mismo id, y sólo tendrá diferentes aquellos
     * datos que se quieran modificar del objeto
     * @param t el objeto que modifica, copia del objeto a modificar pero con los datos modificados
     */
    void update(T t);

    /**
     * Método que elimina un objeto de una lista.
     * @param id el id del objeto a eliminar
     */
    void delete(String id);

    /**
     * Método que convierte la lista personalizada en un objeto del tipo List
     * @return un objeto del tipo List
     */
    List<T> listar();
}
