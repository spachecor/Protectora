package org.project.protectora.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.project.protectora.MainScreen;
import org.project.protectora.models.personas.Usuario;
import org.project.protectora.servicios.bbdd.ConexionBBDD;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NuevoUsuarioController implements Initializable {
    @FXML
    private Button inicio, enviar, limpiar;
    @FXML
    private TextField email, telefono, nombre, dni, ocupacion, direccion, localidad, provincia, codigoPostal;
    @FXML
    private DatePicker fechaNacimiento;
    @FXML
    private Label mensajeEstado;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inicio.setOnAction(e -> {
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(MainScreen.class.getResource("fxml/main-screen.fxml"));
                MainScreen.stage.setScene(new Scene(fxmlLoader.load()));
                MainScreen.stage.setTitle("Inicio");
            }catch(IOException ex){
                System.out.println("Imposible cargar");
            }
        });
        limpiar.setOnAction(e -> {
            clearPage();
        });
        enviar.setOnAction(e -> {
            try{
                subirUsuarioBBDD();
                mensajeEstado.setText("Usuario creado correctamente");
            }catch(Exception ex){
                mensajeEstado.setText("Ha habido un error al crear el usuario");
            }
            clearPage();
        });
    }
    private void subirUsuarioBBDD(){
        //comprobamos que tó el formulario ha sido rellenado
        if(email.getText().isEmpty()||telefono.getText().isEmpty()||nombre.getText().isEmpty()
                ||fechaNacimiento.getValue()==null||dni.getText().isEmpty()||ocupacion.getText().isEmpty()
                ||direccion.getText().isEmpty()||direccion.getText().isEmpty()||localidad.getText().isEmpty()
                ||provincia.getText().isEmpty()||codigoPostal.getText().isEmpty()){
            throw new RuntimeException("No puede haber campos vacíos");
        }
        //creamos el nuevo usuario
        Usuario usuario = new Usuario(
                email.getText(), Integer.parseInt(telefono.getText()), nombre.getText(), fechaNacimiento.getValue(),
                dni.getText(), ocupacion.getText(), direccion.getText(), localidad.getText(), provincia.getText(),
                Integer.parseInt(codigoPostal.getText())
        );
        try{
            ConexionBBDD conexionBBDD = new ConexionBBDD();
            conexionBBDD.insertarUsuario(usuario);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void clearPage(){
        email.clear();
        telefono.clear();
        nombre.clear();
        fechaNacimiento.setValue(null);
        dni.clear();
        ocupacion.clear();
        direccion.clear();
        localidad.clear();
        provincia.clear();
        codigoPostal.clear();
    }
}