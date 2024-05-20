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
    private Button panel, nuevoAnimal, nuevoUsuario;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        panel.setOnAction(e -> {
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(MainScreen.class.getResource("fxml/panel.fxml"));
                MainScreen.stage.setScene(new Scene(fxmlLoader.load()));
                MainScreen.stage.setTitle("Panel");
            }catch(IOException ex){
                System.out.println("Imposible cargar: "+ex.getMessage());
                ex.printStackTrace();
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
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(MainScreen.class.getResource("fxml/nuevoUsuario.fxml"));
                MainScreen.stage.setScene(new Scene(fxmlLoader.load()));
                MainScreen.stage.setTitle("Nuevo usuario");
            }catch(IOException ex){
                System.out.println("Imposible cargar: "+ex.getMessage());
                ex.printStackTrace();
            }
        });

    }
}