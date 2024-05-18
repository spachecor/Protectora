package org.project.protectora.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.project.protectora.MainScreen;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class PanelController implements Initializable {

    @FXML
    private Button inicio, limpiarCampos;
    @FXML
    private ChoiceBox<String> tipoEntidad, campos, tipoModificacion, valorNuevo;
    @FXML
    private TextField textFieldBusqueda, idEntidad, idAnimalTextField, idUsuarioTextField;
    @FXML
    private GridPane gridTarjetas;
    private String[] tiposArray = {"Animal", "Usuario", "Solicitud de Adopción"};
    private String[] camposAnimalArray = {"Nombre", "Sexo"};
    private String[] camposUsuarioArray = {"Nombre", "Sexo"};
    private String[] camposSolicitudAdopcionArray = {"Nombre del animal", "Nombre del usuario"};

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
        //configuramos el choice de los tipos y los campos
        tipoEntidad.getItems().addAll(tiposArray);
        campos.getItems().addAll(camposAnimalArray);
        //ahora colocamos los valores del segundo choice, el de los campos, según el valor el choice tipos
        tipoEntidad.setOnAction(this::setCampos);
        //ahora modificamos el valor del prompt text del textFieldBuscador según la opción que seleccionemos en el choicebox
        //de los campos
        campos.setOnAction(this::setTextBusqueda);

        //asignamos el comportamiento del botón limpiarCampos que limpia todos los campos
        limpiarCampos.setOnAction(e -> {
            idEntidad.setText("");
            tipoEntidad.setValue("");
            campos.setValue("");
            textFieldBusqueda.setText("");
            idAnimalTextField.setText("");
            idUsuarioTextField.setText("");
            tipoModificacion.setValue("");
            valorNuevo.setValue("");
        });
    }

    /**
     * Método que selecciona el contenido del choice según el tipo de entidad que seleccionemos
     * @param actionEvent El evento que genera cuando cambiamos de opcion en el choicebox
     */
    private void setCampos(ActionEvent actionEvent) {
        if(Objects.equals(tipoEntidad.getValue(), tiposArray[0])){
            campos.getItems().clear();
            campos.getItems().addAll(camposAnimalArray);
        }else if(Objects.equals(tipoEntidad.getValue(), tiposArray[1])){
            campos.getItems().clear();
            campos.getItems().addAll(camposUsuarioArray);
        } else if (Objects.equals(tipoEntidad.getValue(), tiposArray[2])) {
            campos.getItems().clear();
            campos.getItems().addAll(camposSolicitudAdopcionArray);
        }
    }
    private void setTextBusqueda(ActionEvent actionEvent) {
        textFieldBusqueda.setPromptText(campos.getValue());
    }
}
