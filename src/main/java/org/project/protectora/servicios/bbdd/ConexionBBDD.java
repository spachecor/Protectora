package org.project.protectora.servicios.bbdd;


import org.project.protectora.models.Entidad;
import org.project.protectora.models.animals.Animal;
import org.project.protectora.models.animals.Gato;
import org.project.protectora.models.animals.Perro;

import java.sql.*;

public class ConexionBBDD {
    private static String url, pwd, user;
    private static Connection connection;

    static {
        url = "jdbc:MySQL://localhost/protectora";
        pwd = "";
        user = "root";
    }
    public ConexionBBDD() throws SQLException {
        connection = DriverManager.getConnection(url, user, pwd);
    }
    public void insertar(Animal animal, String table){
        Gato gato;
        Perro perro;
        String tipo = null;
        if(animal instanceof Gato){
            gato = (Gato)animal;
            tipo = "Gato";
        }else if(animal instanceof Perro){
            perro = (Perro)animal;
            tipo = "Perro";
        }else tipo = "Otro";
        try{
            String query = "INSERT INTO " + table + " VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, animal.getId());
            statement.setString(2, animal.getNombre());
            statement.setString(3, tipo);
            statement.setString(4, animal.getColor().getColor());
            statement.setString(5, animal.getSexo().getSexo());
            statement.setDate(5, Date.valueOf(animal.getFechaNacimiento()));
            //todo seguir por aquí

            /*
fechaEntradaProtectora date,
castrado boolean,
chip long,
raza varchar(50),
tamanio varchar(25),*/
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
