package org.project.protectora.servicios.listapersonalizada;


import org.project.protectora.models.personas.Usuario;
import org.project.protectora.properties.CamposOrdenacion;
import org.project.protectora.properties.DireccionOrdenacion;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class UsuarioLista<T extends Usuario> extends ListaPersonalizadaList<T> {
    @Override
    public void update(T usuario) {
        T u = this.searchForId(usuario.getId());
        u.setNombre(usuario.getNombre());
        u.setOcupacion(usuario.getOcupacion());
        u.setEmail(usuario.getEmail());
        u.setTelefono(usuario.getTelefono());
        u.setDireccion(usuario.getDireccion());
    }

    @Override
    public List<T> listar(CamposOrdenacion campo, DireccionOrdenacion direccion) {
        List<T> listaOrdenada = new ArrayList<>(this.lista);
        listaOrdenada.sort(new Comparator<T>() {
            @Override
            public int compare(T a, T b) {
                int resultado=0;
                if(direccion==DireccionOrdenacion.ASC){
                    resultado = this.ordenar(a, b);
                }else if(direccion==DireccionOrdenacion.DESC){
                    resultado = this.ordenar(b, a);
                }
                return resultado;
            }
            private int ordenar(T a, T b){
                int resultado=0;
                switch (campo){
                    case CamposOrdenacion.ID_USUARIO -> resultado = a.getId().compareTo(b.getId());
                    case CamposOrdenacion.NOMBRE_USUARIO -> resultado = a.getNombre().compareTo(b.getNombre());
                }
                return resultado;
            }
        });
        return listaOrdenada;
    }
}
