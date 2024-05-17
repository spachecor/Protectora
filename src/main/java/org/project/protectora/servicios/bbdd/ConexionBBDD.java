package org.project.protectora.servicios.bbdd;

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
    public void insertarAnimal(Animal animal){
        Gato gato=null;
        Perro perro=null;
        String tipo = null;
        if(animal instanceof Gato){
            gato = (Gato)animal;
            tipo = "Gato";
        }else if(animal instanceof Perro){
            perro = (Perro)animal;
            tipo = "Perro";
        }else tipo = "Otro";
        try{
            String query = "INSERT INTO animal VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, animal.getId());
            statement.setString(2, animal.getNombre());
            statement.setString(3, tipo);
            statement.setString(4, animal.getColor().getColor());
            statement.setString(5, animal.getSexo().getSexo());
            statement.setDate(6, Date.valueOf(animal.getFechaNacimiento()));
            statement.setDate(7, Date.valueOf(animal.getFechaEntradaProtectora()));
            statement.setBoolean(8, animal.getCastrado());
            statement.setLong(9, (animal.getChip()==null)?0:animal.getChip());
            if(animal instanceof Gato){
                statement.setString(10, gato.getRaza().getRaza());
                statement.setString(11, gato.getTamanio().getTamanio());
            }else if(animal instanceof Perro){
                statement.setString(10, perro.getRaza().getRaza());
                statement.setString(11, perro.getTamanio().getTamanio());
            }else {
                statement.setString(10, null);
                statement.setString(11, null);
            }
            statement.setBytes(12, animal.getImg());

            statement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Error al insertar el animal");
        }
    }
    public int contarAnimales(){
        int resultado=0;
        try{
            String query= "SELECT COUNT(*) FROM animal";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                resultado=resultSet.getInt(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultado;
    }
    public int contarUsuarios(){
        int resultado=0;
        try{
            String query= "SELECT COUNT(*) FROM usuario";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                resultado=resultSet.getInt(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultado;
    }
    public int contarsolicitudes(){
        int resultado=0;
        try{
            String query= "SELECT COUNT(*) FROM solicitudAdopcion";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                resultado=resultSet.getInt(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultado;
    }
}
