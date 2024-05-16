package org.project.protectora.servicios.listapersonalizada;

import org.project.protectora.comportamiento.Crudable;
import org.project.protectora.comportamiento.Ordenable;
import org.project.protectora.comportamiento.Paginable;
import org.project.protectora.models.Entidad;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que define el comportamiento de la ListaPersonalizadaList, que es una lista con comportamiento personalizado
 * @param <T> el tipo de objeto que maneja esta lista, que debe heredar de Entidad
 * @author Selene
 * @version 1.0
 */
public abstract class ListaPersonalizadaList<T extends Entidad> implements Crudable<T>, Ordenable<T>, Paginable<T>, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    //declaramos un objeto del tipo list para usarlo en la lista y lo instanciamos como ArrayList en el constructor
    List<T> lista;
    public ListaPersonalizadaList(){
        this.lista = new ArrayList<>();
    }
    /**
     * Método que cuenta el nº de registros que contiene la lista
     * @return el nº de registros que tiene la lista
     */
    public int count(){
        return lista.size();
    }
    /**
     * Se recorre la lista, si el id que entra como parámetro coincide con alguno de la lista, se devuelve el objeto
     * al que pertenece ese id, si no, se devuelve null
     * @param id el id único de cada objeto
     * @return devuelve el objeto encontrado en caso de encontrarlo, y null en caso de no encontrar nada
     */
    @Override
    public T searchForId(String id) {
        T result = null;
        for(T t:lista){
            if(t.getId().equals(id)){
                result=t;
                break;
            }
        }
        return result;
    }
    @Override
    public void add(T t) {
        this.lista.add(t);
    }
    @Override
    public void delete(String id) {
        //utilizamos el método remove, pero como éste pide el objeto a eliminar, lo buscamos con nuestro método .seachForId()
        this.lista.remove(this.searchForId(id));
    }
    @Override
    public List<T> listar() {
        return this.lista;
    }
    @Override
    public List<T> listar(int limit, int offset) {
        //usamos el método sublist para sacar una sublista con los límites introducidos por parámetro
        return lista.subList(limit, offset);
    }
}
