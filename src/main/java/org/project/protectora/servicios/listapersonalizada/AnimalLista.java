package org.project.protectora.servicios.listapersonalizada;

import org.project.protectora.models.animals.Animal;
import org.project.protectora.properties.CamposOrdenacion;
import org.project.protectora.properties.DireccionOrdenacion;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Clase que define una lista personalizada de objetos del tipo de dato Animal
 * @param <T> el tipo de dato, que será o heredará de Animal
 * @author Selene
 * @version 1.0
 */
public class AnimalLista<T extends Animal> extends ListaPersonalizadaList<T>{
    @Override
    public void update(T t) {
        //primero creamos un objeto del tipo animal para tener una copia del animal que ha llamado al método, el animal a modificar
        //que deberá compartir el id con el objeto a modificar
        T animal = this.searchForId(t.getId());
        //ahora hacemos uso de los set correspondientes para modificar los datos modificables del animal
        animal.setNombre(t.getNombre());
        animal.setCastrado(t.getCastrado());
        //si ya tiene un chip no se le puede volver a poner otro
        if(animal.getChip()==null)animal.setChip(t.getChip());
    }
    @Override
    public List<T> listar(CamposOrdenacion campo, DireccionOrdenacion direccion) {
        //para no modificar nuestra lista original, creamos una nueva lista para ordenarla
        List<T> listaOrdenada = new ArrayList<>(this.lista);
        /*//ordenamos usando la interfaz comparator implementándola con clase anónima
        //el método .sort ordenará según el valor que devuelva el método compare
        listaOrdenada.sort(new Comparator<>() {
            @Override
            public int compare(T a, T b) {
                int resultado = 0;
                //vamos a poder ordenar por id, sexo, edad, tiempo en la protectora y estado
                if (direccion == DireccionOrdenacion.ASC) {
                    resultado = this.ordenar(a, b);//llamamos al método y le pasamos los dos animales
                } else if (direccion == DireccionOrdenacion.DESC) {
                    resultado = this.ordenar(b, a);//lamamos al método ordenar intercambiando las posiciones, para que ordene de forma descendente
                }
                return resultado;
            }

            //creamos un método dentro de la clase anónima, al que le asignamos que es privado pues sólo lo usaremos en la clase anónima
            private int ordenar(T a, T b) {
                int resultado = 0;
                //dependiento del campo escogido, devolverá un nº u otro
                switch (campo) {
                    case CamposOrdenacion.ID_ANIMAL -> resultado = a.getId().compareTo(b.getId());
                    case CamposOrdenacion.SEXO_ANIMAL -> resultado = a.getSexo().compareTo(b.getSexo());
                    case CamposOrdenacion.NOMBRE_ANIMAL -> resultado = a.getNombre().compareTo(b.getNombre());
                    case CamposOrdenacion.EDAD_ANIMAL -> resultado = a.getEdad().compareTo(b.getEdad());
                    case CamposOrdenacion.TIEMPO_EN_PROTECTORA -> resultado = a.getTiempoEnProtectora().compareTo(b.getTiempoEnProtectora());
                    case CamposOrdenacion.ESTADO_ANIMAL -> resultado = a.getEstado().compareTo(b.getEstado());
                }
                //devuelve el resultado
                return resultado;
            }
        });*/
        return listaOrdenada;
    }
}
