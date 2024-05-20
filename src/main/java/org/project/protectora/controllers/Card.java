package org.project.protectora.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.project.protectora.MainScreen;
import org.project.protectora.models.Entidad;
import org.project.protectora.models.adopcion.SolicitudAdopcion;
import org.project.protectora.models.animals.Animal;
import org.project.protectora.models.personas.Usuario;


public class Card {
    @FXML
    private ImageView imagen;
    @FXML
    private Label descripcion;

    public void setData(Entidad entidad) {
        if(entidad instanceof Animal){
            Image image = new Image(
                    "file:///C:\\Users\\Selene\\Documents\\workspace-java\\" +
                       "Protectora\\src\\main\\resources\\org\\project\\protectora\\img\\animal\\"+entidad.getId()+".jpg"
            );
            imagen.setImage(image);
        }else if (entidad instanceof Usuario){
            Image image = new Image(MainScreen.class.getResourceAsStream("img/usuarioBase.jpg"));
            imagen.setImage(image);
        }else if (entidad instanceof SolicitudAdopcion){
            Image image = new Image(MainScreen.class.getResourceAsStream("img/solicitudBase.jpg"));
            imagen.setImage(image);
        }
        descripcion.setText(entidad.getDescripcion());
    }
}
