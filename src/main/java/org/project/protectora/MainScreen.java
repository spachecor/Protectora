package org.project.protectora;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.project.protectora.models.adopcion.SolicitudAdopcion;
import org.project.protectora.models.animals.Animal;
import org.project.protectora.models.personas.Usuario;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class MainScreen extends Application {
    //tomamos la stage como propiedad static para poder modificarla con cambios de escenas
    public static Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        //asignamos a la propiedad estática de la ventana la ventana de nuestra app
        MainScreen.stage = stage;
        //ajustamos los contadores de las entidades
        Animal.reestablecerContador();
        Usuario.reestablecerContador();
        SolicitudAdopcion.reestablecerContador();

        FXMLLoader fxmlLoader = new FXMLLoader(MainScreen.class.getResource("fxml/main-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("img/icono.png"))));
        stage.setTitle("Inicio");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}