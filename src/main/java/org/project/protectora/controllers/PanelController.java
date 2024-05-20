package org.project.protectora.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.project.protectora.MainScreen;
import org.project.protectora.models.Entidad;
import org.project.protectora.models.adopcion.SolicitudAdopcion;
import org.project.protectora.servicios.bbdd.ConexionBBDD;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class PanelController implements Initializable {

    @FXML
    private Button inicio, limpiarCampos, buscarPorId, buscarFiltrado, solicitar, modificar;
    @FXML
    private ChoiceBox<String> tipoEntidad, campos, tipoModificacion, entidadModificable;
    @FXML
    private TextField textFieldBusqueda, idEntidad, idAnimalTextField, idUsuarioTextField, valorNuevo;
    @FXML
    private VBox cardContainer;
    @FXML
    private Label respuestaSolicitud;
    private String[] tiposArray = {"Animal", "Usuario", "Solicitud de Adopción", "Todos"};
    private String[] camposAnimalArray = {"Nombre", "Tipo", "Sexo", "Color"};
    private String[] camposUsuarioArray = {"Nombre", "DNI", "Email"};
    private String[] camposSolicitudAdopcionArray = {"Id animal", "Id adoptante"};
    private String[] camposModificablesArray = {"Eliminar", "Nombre"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //cargamos el panel con la configuración de base
        cargarPanelInicial();
        //asignamos el comportamiento del botón limpiarCampos que limpia todos los campos
        limpiarCampos.setOnAction(e -> {
            setLimpiarCampos();
        });
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
        //asignamos la búsqueda por id
        buscarPorId.setOnAction(e -> {
            //buscamos la entidad que coincida con el id
            List<Entidad> entidades = buscarPorIdMethod();
            //limpiamos las entidades anteriores
            cardContainer.getChildren().clear();
            //cargamos las nuevas entidades
            cargarEntidades(entidades);
        });
        //BÚSQUEDA POR TIPO, CAMPO Y VALOR
        buscarFiltrado.setOnAction(e -> {
            try{
                ConexionBBDD conexionBBDD = new ConexionBBDD();
                List<Entidad> entidades;
                //consideramos el caso en el que el usuario desee ver todas las entidades
                if(tipoEntidad.getValue().equals(tiposArray[3])){
                    entidades = conexionBBDD.obtenerEntidadesDeBBDD();
                }else{
                    String tipo = tipoEntidad.getValue().toLowerCase();
                    String campo = campos.getValue().toLowerCase();
                    String valor = textFieldBusqueda.getText();
                    //reajustamos en el caso de que sea una solicitud de adopcion
                    if(tipoEntidad.getValue().equals(tiposArray[2])){
                        tipo = "solicitudAdopcion";
                        if(campos.getValue().equals(camposSolicitudAdopcionArray[0]))campo = "animal";
                        if(campos.getValue().equals(camposSolicitudAdopcionArray[1]))campo = "adoptante";
                    }
                    //con los valores asignados, buscamos la entidad
                    entidades = conexionBBDD.buscarEntidadesPorTablaYCampo(tipo, campo, valor);

                }
                //limpiamos de la anterior busqueda
                cardContainer.getChildren().clear();
                //mostramos la nueva busqueda
                cargarEntidades(entidades);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        });
        solicitar.setOnAction(e -> {
            try{
                ConexionBBDD conexionBBDD = new ConexionBBDD();
                conexionBBDD.insertarSolicitudAdopcion(new SolicitudAdopcion(ConexionBBDD.buscarAnimalPorId(idAnimalTextField.getText()),
                        ConexionBBDD.buscarUsuarioPorId(idUsuarioTextField.getText())));
                respuestaSolicitud.setText("Solicitud creada correctamente");
            }catch (Exception ex){
                respuestaSolicitud.setText("Error al insertar solicitud");
                ex.printStackTrace();
            }
        });
        modificar.setOnAction(e -> {
            //si escogemos eliminar
            if(tipoModificacion.getValue().equals(camposModificablesArray[0])){
            try{
                ConexionBBDD conexionBBDD = new ConexionBBDD();
                conexionBBDD.eliminarPorId(entidadModificable.getValue());
                respuestaSolicitud.setText("Entidad eliminada correctamente");
            }catch (Exception ex){
                respuestaSolicitud.setText("Error al eliminar la entidad");
            }
            //si escogemos modificar el nombre
            } else if (tipoModificacion.getValue().equals(camposModificablesArray[1])) {
                try{
                    if (valorNuevo.getText().isEmpty())throw new RuntimeException("El nuevo valor no puede estar vacío");
                    ConexionBBDD conexionBBDD = new ConexionBBDD();
                    conexionBBDD.modificarPorId(entidadModificable.getValue(), valorNuevo.getText());
                    respuestaSolicitud.setText("Entidad modificada correctamente");
                }catch (Exception ex){
                    respuestaSolicitud.setText("Error al modificar la entidad");
                    ex.printStackTrace();
                }
            }
        });
    }

    /**
     * Metodo que carga en la parte derecha del panel las entidades que existen en la lista pasada por parámetro.
     * IMPORTANTE: NO BORRA LAS ANTERIORES TARJETAS, HAY QUE BORRARLAS ANTES DE CARGAR LAS NUEVAS
     * @param entidades lista de entidades para cargar en el panel
     */
    private void cargarEntidades(List<Entidad> entidades){
        try{
            //creamos un arraylist de ids para luego asignarlas al deplegable de entidades modificables
            List<String> ids = new ArrayList<>();
            //iteramos la lista agregando las tarjetas
            for(Entidad entidad : entidades){
                //cargamos la vista de la tarjeta
                FXMLLoader fxmlLoader = new FXMLLoader(MainScreen.class.getResource("fxml/card.fxml"));
                HBox cardHBox = fxmlLoader.load();
                //tomamos el controlador de la tarjeta
                Card cardController = fxmlLoader.getController();
                //asignamos los valores (imagen y descripción) de la tarjeta según la entidad
                cardController.setData(entidad);
                //agregamos la nueva tarjeta
                cardContainer.getChildren().add(cardHBox);
                //agregamos el id de la entidad al arraylist de ids
                ids.add(entidad.getId());
            }
            //una vez terminado, asignamos al choicebox de las entidades modificables aquellas que han salido en la búsqueda
            actualizarEntidadesModificables(ids);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private List<Entidad> buscarPorIdMethod(){
        List<Entidad> entidades = new ArrayList<>();
        //1º creamos una entidad y la igualamos a null
        Entidad entidad = null;
        //buscamos por id en los animales, si no existe, entidad seguirá en null, si existe contendrá el objeto en cuestión
        entidad = ConexionBBDD.buscarAnimalPorId(idEntidad.getText());
        //si existe la entidad, la agregamos a la lista
        if(entidad != null)entidades.add(entidad);
        else {
            //si no la ha encontrado en los animales, buscamos en los usuarios
            entidad = ConexionBBDD.buscarUsuarioPorId(idEntidad.getText());
            //si existe la entidad, la agregamos a la lista
            if(entidad != null)entidades.add(entidad);
            else {
                //si sigue siendo null, se busca en las solicitudes
                entidad = ConexionBBDD.buscarSolicitudPorId(idEntidad.getText());
                //si existe la entidad, la agregamos a la lista
                if(entidad != null)entidades.add(entidad);
                else System.out.println("La entidad no existe");
            }
        }
        System.out.println(entidad);
        return entidades;
    }
    private void cargarPanelInicial(){
        //cargamos las entidades en el panel de la derecha
        try{
            ConexionBBDD conexionBBDD = new ConexionBBDD();
            cargarEntidades(conexionBBDD.obtenerEntidadesDeBBDD());
        }catch (Exception e){
            e.printStackTrace();
        }
        //configuramos el choice de los tipos
        tipoEntidad.getItems().addAll(tiposArray);
        //ahora colocamos los valores del segundo choice, el de los campos, según el valor el choice tipos
        tipoEntidad.setOnAction(this::setCampos);
        //ahora modificamos el valor del prompt text del textFieldBuscador según la opción que seleccionemos en el choicebox
        //de los campos
        campos.setOnAction(this::setTextBusqueda);
        //asignamos los campos modificables con el metodo cuando se escoja una entidad
        entidadModificable.setOnAction(this::setTipoModificacion);
        tipoModificacion.setOnAction(e -> {
            modificar.setDisable(false);
        });
        tipoModificacion.setDisable(true);
        valorNuevo.setDisable(true);
        modificar.setDisable(true);
    }
    /**
     * Método que selecciona el contenido del choice según el tipo de entidad que seleccionemos
     * @param actionEvent El evento que genera cuando cambiamos de opcion en el choicebox
     */
    private void setCampos(ActionEvent actionEvent) {
        if(Objects.equals(tipoEntidad.getValue(), tiposArray[0])){
            campos.setDisable(false);
            textFieldBusqueda.setDisable(false);
            textFieldBusqueda.clear();
            campos.getItems().clear();
            campos.getItems().addAll(camposAnimalArray);
        }else if(Objects.equals(tipoEntidad.getValue(), tiposArray[1])){
            campos.setDisable(false);
            textFieldBusqueda.setDisable(false);
            textFieldBusqueda.clear();
            campos.getItems().clear();
            campos.getItems().addAll(camposUsuarioArray);
        } else if (Objects.equals(tipoEntidad.getValue(), tiposArray[2])) {
            campos.setDisable(false);
            textFieldBusqueda.setDisable(false);
            textFieldBusqueda.clear();
            campos.getItems().clear();
            campos.getItems().addAll(camposSolicitudAdopcionArray);
        } else if(Objects.equals(tipoEntidad.getValue(), tiposArray[3])){
            campos.setDisable(true);
            textFieldBusqueda.setDisable(true);
            campos.getItems().clear();
            textFieldBusqueda.clear();
        }
    }
    private void setTextBusqueda(ActionEvent actionEvent) {
        textFieldBusqueda.setPromptText(campos.getValue());
        textFieldBusqueda.clear();
    }
    private void setTipoModificacion(ActionEvent actionEvent) {
        tipoModificacion.getItems().clear();
        tipoModificacion.setDisable(false);
        modificar.setDisable(true);
        //si no es una solicitud de adopcion, se cargan las dos opciones
        if(!String.valueOf(entidadModificable.getValue().charAt(0)).equals("s")){
            tipoModificacion.getItems().addAll(camposModificablesArray);
            valorNuevo.setDisable(false);
        }else{
            //si es una solicitud de adopcion, solo se da la opcion de eliminar
            tipoModificacion.getItems().addAll(camposModificablesArray[0]);
            valorNuevo.setDisable(true);
        }

    }
    private void setLimpiarCampos(){
        idEntidad.clear();
        tipoEntidad.setValue("");
        campos.setValue("");
        textFieldBusqueda.clear();
        idAnimalTextField.clear();
        idUsuarioTextField.clear();
        tipoModificacion.setValue("");
        valorNuevo.clear();
    }
    private void actualizarEntidadesModificables(List<String> ids){
        entidadModificable.getItems().clear();
        entidadModificable.getItems().addAll(ids);
    }
}
