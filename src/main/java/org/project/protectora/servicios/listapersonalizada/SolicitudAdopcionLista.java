package org.project.protectora.servicios.listapersonalizada;

import org.project.protectora.models.adopcion.SolicitudAdopcion;
import org.project.protectora.properties.CamposOrdenacion;
import org.project.protectora.properties.DireccionOrdenacion;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SolicitudAdopcionLista<T extends SolicitudAdopcion> extends ListaPersonalizadaList<T>{

    @Override
    public void update(T solicitudAdopcion) {
        throw new RuntimeException("No se puede modificar una solicitud de adopción");
    }

    @Override
    public List<T> listar(CamposOrdenacion campo, DireccionOrdenacion direccion) {
        //para no modificar nuestra lista original, creamos una nueva lista para ordenarla
        List<T> listaOrdenada = new ArrayList<>(this.lista);
        //ordenamos usando la interfaz comparator implementándola con clase anónima
        //el método .sort ordenará según el valor que devuelva el método compare
        listaOrdenada.sort(new Comparator<>() {
            @Override
            public int compare(T a, T b) {
                int resultado = 0;
                //se podrá ordenar por id de la solicitud de adopcion, nombre del animal y nombre del adoptante
                if(direccion==DireccionOrdenacion.ASC){
                    resultado = this.ordenar(a, b);//llamamos al método y le pasamos las dos solicitudes
                }else if(direccion==DireccionOrdenacion.DESC){
                    resultado = this.ordenar(b, a);//lamamos al método ordenar intercambiando las posiciones, para que ordene de forma descendente
                }

                return resultado;
            }
            //creamos un método dentro de la clase anónima, al que le asignamos que es privado pues sólo lo usaremos en la clase anónima
            private int ordenar(T a, T b){
                int resultado = 0;
                //dependiendo del campo escogido, devolverá un nº u otro
                switch (campo){
                    case CamposOrdenacion.ID_SOLICITUD -> resultado=a.getId().compareTo(b.getId());
                    case CamposOrdenacion.ID_ANIMAL -> resultado=a.getAnimal().getId().compareTo(b.getAnimal().getId());
                    case CamposOrdenacion.ID_USUARIO -> resultado=a.getAdoptante().getId().compareTo(b.getAdoptante().getId());
                }
                return resultado;
            }
        });
        return listaOrdenada;
    }
}
