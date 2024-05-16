package org.project.protectora.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.project.protectora.MainScreen;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class BuscadorController implements Initializable {

    @FXML
    private Button inicio, limpiarCampos;
    @FXML
    private ChoiceBox<String> tipos, campos;
    private String[] tiposArray = {"Animal", "Usuario", "Solicitud de Adopción"};
    private String[] camposAnimalArray = {"Nombre", "Sexo"};
    private String[] camposUsuarioArray = {"Nombre", "Sexo"};
    private String[] camposSolicitudAdopcionArray = {"Nombre del animal", "Nombre del usuario"};
    @FXML
    private TextField textFieldBusqueda, idEntidad;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //configuramos el botón de salida
        inicio.setOnAction(e -> {
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(MainScreen.class.getResource("fxml/main-screen.fxml"));
                MainScreen.stage.setScene(new Scene(fxmlLoader.load()));
                MainScreen.stage.setTitle("Inicio");
            }catch(IOException ex){
                System.out.println("Imposible cargar");
            }
        });
        //configuramos el choice de los tipos
        tipos.getItems().addAll(tiposArray);
        //colocamos el valor de serie
        tipos.setValue(tiposArray[0]);
        campos.getItems().addAll(camposAnimalArray);
        //ahora colocamos los valores del segundo choice, el de los campos, según el valor el choice tipos
        tipos.setOnAction(this::setCampos);
        //ahora modificamos el valor del prompt text del textFieldBuscador según la opción que seleccionemos en el choicebox
        //de los campos
        campos.setOnAction(this::setTextBusqueda);

        //asignemos el comportamiento del botón limpiarCampos que limpia todos los campos
        limpiarCampos.setOnAction(e -> {
            textFieldBusqueda.setText("");
            idEntidad.setText("");
            tipos.setValue(tiposArray[0]);
        });


    }

    /**
     * Método que selecciona el contenido del choice según el tipo de entidad que seleccionemos
     * @param actionEvent El evento que genera cuando cambiamos de opcion en el choicebox
     */
    private void setCampos(ActionEvent actionEvent) {
        if(Objects.equals(tipos.getValue(), tiposArray[0])){
            campos.getItems().clear();
            campos.getItems().addAll(camposAnimalArray);
        }else if(Objects.equals(tipos.getValue(), tiposArray[1])){
            campos.getItems().clear();
            campos.getItems().addAll(camposUsuarioArray);
        } else if (Objects.equals(tipos.getValue(), tiposArray[2])) {
            campos.getItems().clear();
            campos.getItems().addAll(camposSolicitudAdopcionArray);
        }
    }
    private void setTextBusqueda(ActionEvent actionEvent) {
        textFieldBusqueda.setPromptText(campos.getValue());
    }
}
