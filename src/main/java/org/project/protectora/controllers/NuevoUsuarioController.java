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

public class NuevoUsuarioController implements Initializable {
    @FXML
    private Button inicio;
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
    }
}