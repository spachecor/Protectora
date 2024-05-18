package org.project.protectora;

import org.project.protectora.models.Entidad;
import org.project.protectora.servicios.bbdd.ConexionBBDD;

import java.sql.SQLException;
import java.util.List;

public class test {
    public static void main(String[] args) {
        try{
            ConexionBBDD conexionBBDD = new ConexionBBDD();
            List<Entidad> entidades = conexionBBDD.obtenerEntidadesDeBBDD();
            entidades.forEach(System.out::println);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
