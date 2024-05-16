package org.project.protectora.controllers;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.project.protectora.MainScreen;
import org.project.protectora.models.animals.Gato;
import org.project.protectora.models.animals.Otro;
import org.project.protectora.models.animals.Perro;
import org.project.protectora.properties.*;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class NuevoAnimalController implements Initializable {
    @FXML
    private Button inicio, enviar, limpiar, examinar;
    @FXML
    private TextField nombreAnimal, numeroChip;
    @FXML
    private ChoiceBox<String> colores, sexos, castrado, chip, tipos, razas, tamanios;
    @FXML
    private Label mensajeExaminar;

    private String[] colorArray = {Color.MARRON_OSCURO.getColor().replace("-", " "), Color.MARRON_CLARO.getColor().replace("-", " "), Color.BLANCO.getColor(),
    Color.NEGRO.getColor(), Color.BICOLOR.getColor(), Color.TRICOLOR.getColor(), Color.NARANJA.getColor(), Color.AZUL.getColor()};
    private String[] sexosArray = {Sexo.HEMBRA.getSexo(), Sexo.MACHO.getSexo()};
    private String[] siNoArray = {"Sí", "No"};
    private String[] tiposArray = {Gato.class.getName().substring(Gato.class.getName().lastIndexOf(".")+1),
            Perro.class.getName().substring(Perro.class.getName().lastIndexOf(".")+1),
            Otro.class.getName().substring(Otro.class.getName().lastIndexOf(".")+1)};
    private String[] razasGatoArray = {RazaGato.PERSA.getRaza(), RazaGato.ESFINGE.getRaza(), RazaGato.SIAMES.getRaza(), RazaGato.MAINE_COON.getRaza().replace("-", " "),
            RazaGato.BENGALI.getRaza(), RazaGato.RAGDOLL.getRaza(), RazaGato.SIBERIANO.getRaza(), RazaGato.ANGORA.getRaza(),
            RazaGato.CORNISH_REX.getRaza().replace("-", " "), RazaGato.COMUN_EUROPEO.getRaza().replace("-", " "),
            RazaGato.ORIENTAL.getRaza(), RazaGato.EXOTICO.getRaza(), RazaGato.SOMALI.getRaza(), RazaGato.CURL_AMERICANO.getRaza().replace("-", " "),
            RazaGato.AZUL_RUSO.getRaza().replace("-", " "), RazaGato.ABISINIO.getRaza(), RazaGato.CARTUJO.getRaza(),
            RazaGato.BOMBAY.getRaza(), RazaGato.BOSQUE_NORUEGA.getRaza().replace("-", " ")};
    private String[] razasPerroArray = {RazaPerro.CHIHUAHUA.getRaza(), RazaPerro.POMERANIA.getRaza(), RazaPerro.BICHON_MALTES.getRaza().replace("-", " "),
            RazaPerro.CANICHE.getRaza(), RazaPerro.BULLDOG_FRANCES.getRaza().replace("-", " "), RazaPerro.YORKSHIRE.getRaza(),
            RazaPerro.BEAGLE.getRaza(), RazaPerro.BOXER.getRaza(), RazaPerro.JACK_RUSSELL.getRaza().replace("-", " "),
            RazaPerro.BORDER_COLLIE.getRaza().replace("-", " "), RazaPerro.BULL_TERRIER.getRaza().replace("-", " "),
            RazaPerro.CARLINO.getRaza(), RazaPerro.MESTIZO.getRaza(), RazaPerro.GALGO.getRaza(), RazaPerro.HUSKY_SIBERIANO.getRaza().replace("-", " "),
            RazaPerro.SHIBA_INU.getRaza().replace("-", " "), RazaPerro.DALMATA.getRaza(), RazaPerro.SHAR_PEI.getRaza().replace("-", " "),
            RazaPerro.LABRADOR.getRaza(), RazaPerro.SAN_BERNARDO.getRaza().replace("-", " "), RazaPerro.ROTTWEILER.getRaza(),
            RazaPerro.BOBTAIL.getRaza(), RazaPerro.DOGO_ARGENTINO.getRaza().replace("-", " "), RazaPerro.GRAN_DANES.getRaza().replace("-", " ")};
    private String[] tamaniosArray = {Tamanio.GIGANTE.getTamanio(), Tamanio.GRANDE.getTamanio(), Tamanio.MEDIANO.getTamanio(),
            Tamanio.PEQUENIO.getTamanio()};

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
        //configuramos lo que ocurre al enviar el formulario
        enviar.setOnAction(e -> {});
        //configuramos la limpieza del formulario
        limpiar.setOnAction(e -> {
            nombreAnimal.setText("");
        });
        //agregamos las posibilidades a los choicebox
        colores.getItems().addAll(colorArray);
        sexos.getItems().addAll(sexosArray);
        castrado.getItems().addAll(siNoArray);
        chip.getItems().addAll(siNoArray);
        tipos.getItems().addAll(tiposArray);

        //aplicamos comportamientos según las opciones de los choicebox
        chip.setOnAction(this::desactivarIntroducirChip);
        tipos.setOnAction(this::asignarRazasYTamanios);

        //damos funcion al boton examinar, que toma una imagen que introduce el usuario y la guarda
        examinar.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image files", "*.jpg"));
            File imagenAGuardar = fileChooser.showSaveDialog(MainScreen.stage);
            File ubiImagenGuardada = new File(System.getProperty("user.dir")+"/src/main/resources/org/project/protectora/img/animal/img.jpg");
            if(imagenAGuardar != null){
                try{
                    ImageIO.write(SwingFXUtils.fromFXImage(new Image(imagenAGuardar.toURI().toString()), null), "jpg", ubiImagenGuardada);
                }catch(IOException ex){
                    System.out.println("Imposible guardar");
                    ex.printStackTrace();
                }
            }
        });

    }
    /**
     * Método que selecciona si está o no deshabilitada la opción de introducir un chip según la opción que seleccionemos
     * @param actionEvent El evento que genera cuando cambiamos de opcion en el choicebox
     */
    private void desactivarIntroducirChip(ActionEvent actionEvent){
        //si el animal tiene chip, se deja introducir. En cualquier otro caso no se deja
        if(Objects.equals(chip.getValue(), siNoArray[0])){
            numeroChip.setDisable(false);
        }else numeroChip.setDisable(true);
    }

    /**
     * Método que asigna las razas y tamaños según el tipo de animal que selecciones
     * @param actionEvent El evento que genera cuando cambiamos de opción en el choicebox
     */
    private void asignarRazasYTamanios(ActionEvent actionEvent){
        if(Objects.equals(tipos.getValue(), tiposArray[0])){
            //asignamos razas
            razas.setDisable(false);
            razas.getItems().clear();
            razas.getItems().addAll(razasGatoArray);
            //asignamos tamaños
            tamanios.setDisable(false);
            tamanios.getItems().clear();
            tamanios.getItems().addAll(tamaniosArray[1], tamaniosArray[2], tamaniosArray[3]);
        }else if(Objects.equals(tipos.getValue(), tiposArray[1])){
            razas.setDisable(false);
            razas.getItems().clear();
            razas.getItems().addAll(razasPerroArray);
            tamanios.setDisable(false);
            tamanios.getItems().clear();
            tamanios.getItems().addAll(tamaniosArray);
        }else{
            razas.setDisable(true);
            tamanios.setDisable(true);
        }
    }
}
