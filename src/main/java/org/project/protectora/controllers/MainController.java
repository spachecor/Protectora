package org.project.protectora.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import org.project.protectora.MainScreen;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Button buscador, solicitarAdopcion, nuevoAnimal, nuevoUsuario;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buscador.setOnAction(e -> {
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(MainScreen.class.getResource("fxml/buscador.fxml"));
                MainScreen.stage.setScene(new Scene(fxmlLoader.load()));
                MainScreen.stage.setTitle("Buscador");
            }catch(IOException ex){
                System.out.println("Imposible cargar: "+ex.getMessage());
            }
        });
        solicitarAdopcion.setOnAction(e -> {
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(MainScreen.class.getResource("fxml/solicitarAdopcion.fxml"));
                MainScreen.stage.setScene(new Scene(fxmlLoader.load()));
                MainScreen.stage.setTitle("Solicitar adopción");
            }catch(IOException ex){
                System.out.println("Imposible cargar: "+ex.getMessage());
            }
        });
        nuevoAnimal.setOnAction(e -> {
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(MainScreen.class.getResource("fxml/nuevoAnimal.fxml"));
                MainScreen.stage.setScene(new Scene(fxmlLoader.load()));
                MainScreen.stage.setTitle("Nuevo animal");
            }catch(IOException ex){
                System.out.println("Imposible cargar: "+ex.getMessage());
            }
        });
        nuevoUsuario.setOnAction(e -> {
            System.out.println("se ha pulsado nuevo usuario");
        });

    }
}