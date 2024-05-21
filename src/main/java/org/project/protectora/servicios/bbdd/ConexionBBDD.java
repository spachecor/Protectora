package org.project.protectora.servicios.bbdd;

import org.project.protectora.models.Entidad;
import org.project.protectora.models.adopcion.SolicitudAdopcion;
import org.project.protectora.models.animals.Animal;
import org.project.protectora.models.animals.Gato;
import org.project.protectora.models.animals.Otro;
import org.project.protectora.models.animals.Perro;
import org.project.protectora.models.personas.Usuario;
import org.project.protectora.properties.*;
import org.project.protectora.properties.Color;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Path;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Clase que se encarga de hacer la conexión con la base de datos y las diferentes operaciones con ésta
 * @author Selene
 * @version 1.0
 */
public class ConexionBBDD {
    private static String url, pwd, user;
    private static Connection connection;

    static {
        url = "jdbc:MySQL://localhost/protectora";
        pwd = "";
        user = "root";
    }

    /**
     * Constructor que inicializa la conexión con la base de datos
     * @throws SQLException Excepción que puede saltar si falla la conexión
     */
    public ConexionBBDD() throws SQLException {
        connection = DriverManager.getConnection(url, user, pwd);
    }

    /**
     * Método que clasifica e inserta el animal pasado por parámetro en la base de datos
     * @param animal el animal a insertar
     */
    public void insertarAnimal(Animal animal){
        Gato gato=null;
        Perro perro=null;
        String tipo = null;
        if(animal instanceof Gato){
            gato = (Gato)animal;
            tipo = "Gato";
        }else if(animal instanceof Perro){
            perro = (Perro)animal;
            tipo = "Perro";
        }else tipo = "Otro";
        try{
            String query = "INSERT INTO animal VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, animal.getId());
            statement.setString(2, animal.getNombre());
            statement.setString(3, tipo);
            statement.setString(4, animal.getColor().getColor());
            statement.setString(5, animal.getSexo().getSexo());
            statement.setDate(6, Date.valueOf(animal.getFechaNacimiento()));
            statement.setDate(7, Date.valueOf(animal.getFechaEntradaProtectora()));
            statement.setBoolean(8, animal.getCastrado());
            statement.setLong(9, (animal.getChip()==null)?0:animal.getChip());
            if(animal instanceof Gato){
                statement.setString(10, gato.getRaza().getRaza());
                statement.setString(11, gato.getTamanio().getTamanio());
            }else if(animal instanceof Perro){
                statement.setString(10, perro.getRaza().getRaza());
                statement.setString(11, perro.getTamanio().getTamanio());
            }else {
                statement.setString(10, null);
                statement.setString(11, null);
            }
            statement.setBytes(12, animal.getImg());

            statement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Error al insertar el animal");
        }
    }

    /**
     * Método que inserta un usuario en la base de datos
     * @param usuario el usuario a insertar
     * @throws SQLException excepción que puede generar al insertar el usuario
     */
    public void insertarUsuario(Usuario usuario) throws SQLException{
        String query = "insert into usuario values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, usuario.getId());
        statement.setString(2, usuario.getEmail());
        statement.setInt(3, usuario.getTelefono());
        statement.setString(4, usuario.getNombre());
        statement.setDate(5, Date.valueOf(usuario.getFechaNacimiento()));
        statement.setString(6, usuario.getDni());
        statement.setString(7, usuario.getOcupacion());
        statement.setString(8, usuario.getDireccion().getDireccion());
        statement.setString(9, usuario.getDireccion().getLocalidad());
        statement.setString(10, usuario.getDireccion().getProvincia());
        statement.setInt(11, usuario.getDireccion().getCodigoPostal());
        statement.executeUpdate();
    }

    /**
     * Método que inserta una solicitud de adopcion en la base de datos
     * @param solicitud la solicitud de adopcion a insertar
     * @throws SQLException la excepcion que puede generar al insertar la solicitud
     */
    public void insertarSolicitudAdopcion(SolicitudAdopcion solicitud) throws SQLException{
        String query = "insert into solicitudAdopcion values (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, solicitud.getId());
        statement.setString(2, solicitud.getAnimal().getId());
        statement.setString(3, solicitud.getAdoptante().getId());
        statement.executeUpdate();
    }

    /**
     * Método que cuenta los animales en la base de datos
     * @return el nº de animales existentes en la base de datos
     */
    public int contarAnimales(){
        int resultado=0;
        try{
            String query= "SELECT COUNT(*) FROM animal";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                resultado=resultSet.getInt(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultado;
    }

    /**
     * Método que cuenta los usuarios en la base de datos
     * @return el nº de usuarios existentes en la base de datos
     */
    public int contarUsuarios(){
        int resultado=0;
        try{
            String query= "SELECT COUNT(*) FROM usuario";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                resultado=resultSet.getInt(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultado;
    }

    /**
     * Método que cuenta las solicitudes de adopción en la base de datos
     * @return el numero de solicitudes en la base de datos
     */
    public int contarsolicitudes(){
        int resultado=0;
        try{
            String query= "SELECT COUNT(*) FROM solicitudAdopcion";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                resultado=resultSet.getInt(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultado;
    }

    /**
     * Método que obtiene de la base de datos una lista de entidades conformada por los usuarios, los animales y las solicitudes
     * de adopcion
     * @return una lista con las entidades contenidas en la base de datos
     */
    public List<Entidad> obtenerEntidadesDeBBDD(){
        List<Entidad> entidades = new ArrayList<>();
        //querys para tomar los animales, los usuarios y las solicitudes de adopción
        String queryUno = "SELECT * FROM animal";
        String queryDos = "SELECT * FROM usuario";
        String queryTres = "SELECT * FROM solicitudAdopcion";
        try{
            Statement statement = connection.createStatement();
            //empezamos con extraer los animales
            ResultSet resultSetUno = statement.executeQuery(queryUno);
            //llamamos al metodo para que cree los animales según la consulta y los añadimos a la lista de entidades
            entidades.addAll(createAnimalFromSelect(resultSetUno));
            //ahora extraemos los usuarios
            ResultSet resultSetDos = statement.executeQuery(queryDos);
            //llamamos al metodo para que cree los usuarios según la consulta y los añadimos a la lista de entidades
            entidades.addAll(createUserFromSelect(resultSetDos));
            //ahora extraemos las solicitudes de adopción
            ResultSet resultSetTres = statement.executeQuery(queryTres);
            //llamamos al metodo para que cree las solicitudes de adopción según la consulta y los añadimos a la lista de entidades
            entidades.addAll(createAdoptionApplicationFromSelect(resultSetTres));
        }catch (Exception e){
            e.printStackTrace();
        }
        return entidades;
    }

    /**
     * Metodo que obtiene de la tabla animal de la base de datos el animal que coincide con el id insertado
     * @param idAnimal el id del animal a buscar
     * @return el animal solicitado. Puede ser null si no lo encuentra
     */
    public static Animal buscarAnimalPorId(String idAnimal){
        Animal animal = null;
        try{
            String query = "SELECT * FROM animal where id like ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, idAnimal);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                String id = rs.getString("id"), nombre = rs.getString("nombre"),
                        color = rs.getString("color"), sexo = rs.getString("sexo");
                LocalDate fechaNacimiento = rs.getDate("fechaNacimiento").toLocalDate(),
                        fechaEntradaProtectora = rs.getDate("fechaEntradaProtectora").toLocalDate();
                boolean castrado = rs.getBoolean("castrado");
                long chip = rs.getLong("chip");
                byte[] img = rs.getBytes("img");

                if(rs.getString("tipo").equals("Gato")){
                    animal = new Gato(id, nombre, Color.dictionary(color.replace("-", " ")),
                            Sexo.dictionary(sexo), fechaNacimiento, fechaEntradaProtectora, castrado,
                            chip, RazaGato.dictionary(rs.getString("raza")),
                            Tamanio.dictionary(rs.getString("tamanio")), img);
                }else if(rs.getString("tipo").equals("Perro")){
                    animal = new Perro(id, nombre, Color.dictionary(color.replace("-", " ")),
                            Sexo.dictionary(sexo), fechaNacimiento, fechaEntradaProtectora, castrado,
                            chip, RazaPerro.dictionary(rs.getString("raza")),
                            Tamanio.dictionary(rs.getString("tamanio")), img);
                }else {
                    animal = new Otro(id, nombre, Color.dictionary(color.replace("-", " ")),
                            Sexo.dictionary(sexo), fechaNacimiento, fechaEntradaProtectora, castrado,
                            chip, img);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return animal;
    }
    /**
     * Metodo que obtiene de la tabla usuario de la base de datos el usuario que coincide con el id insertado
     * @param id el id del usuario a buscar
     * @return el usuario solicitado. Puede ser null si no lo encuentra
     */
    public static Usuario buscarUsuarioPorId(String id){
        Usuario usuario = null;
        try{
            String query = "SELECT * FROM usuario where id like ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                usuario = new Usuario(rs.getString("id"), rs.getString("email"),
                        rs.getInt("telefono"), rs.getString("nombre"),
                        rs.getDate("fechaNacimiento").toLocalDate(), rs.getString("dni"),
                        rs.getString("ocupacion"), rs.getString("direccion"),
                        rs.getString("localidad"), rs.getString("provincia"),
                        rs.getInt("codigoPostal"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return usuario;
    }
    /**
     * Metodo que obtiene de la tabla solicitudAdopcion de la base de datos la solicitud que coincide con el id insertado
     * @param id el id de la solicitud a buscar
     * @return la solicitud solicitada. Puede ser null si no lo encuentra
     */
    public static SolicitudAdopcion buscarSolicitudPorId(String id){
        SolicitudAdopcion solicitud = null;
        try{
            String query = "SELECT * FROM solicitudAdopcion where id like ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                solicitud = new SolicitudAdopcion(rs.getString("id"), buscarAnimalPorId(rs.getString("animal")),
                        buscarUsuarioPorId(rs.getString("adoptante")));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return solicitud;
    }

    /**
     * Método que elimina una entidad de la tabla según el id que le entre
     * @param id el id de la entidad a eliminar
     * @throws SQLException excepcion que puede generar en la eliminacion
     */
    public void eliminarPorId(String id) throws SQLException{
        String tabla = null, comprobacion = String.valueOf(id.charAt(0));
        switch (comprobacion){
            case "a":
                tabla = "animal";
                break;
            case "u":
                tabla = "usuario";
                break;
            case "s":
                tabla = "solicitudAdopcion";
                break;
        }
        String query = "delete from "+tabla+" where id like ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, id);
        ps.executeUpdate();
    }

    /**
     * Metodo que modifica una entidad segun el id que le entre
     * @param id el id del campo a modificar
     * @param nuevoValor el nuevo valor del campo
     * @throws SQLException excepcion que puede generar en la modificacion
     */
    public void modificarPorId(String id, String nuevoValor) throws SQLException{
        String tabla = null, comprobacion = String.valueOf(id.charAt(0));
        switch (comprobacion){
            case "a":
                tabla = "animal";
                break;
            case "u":
                tabla = "usuario";
                break;
            case "s":
                tabla = "solicitudAdopcion";
                break;
        }
        String query = "update "+tabla+" set nombre = '"+nuevoValor+"' where id like ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, id);
        ps.executeUpdate();
    }

    /**
     * Metodo que recibe el path de una imagen y que la convierte en una array de bytes
     * @param path el path de la imagen
     * @return un array de bytes que representa la imagen
     */
    public static byte[] convertImgToBytes(Path path){
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             FileInputStream fileInputStream = new FileInputStream(path.toFile())) {
            byte[] data = new byte[1024000];
            int readNum;
            while ((readNum = fileInputStream.read(data)) != -1) {
                byteArrayOutputStream.write(data, 0, readNum);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Metodo que convierte un array de bytes en una imagen y la guarda dentro del proyecto
     * @param bytes el array de bytes que recoge toda la información de la imagen
     * @param nombreImg el nuevo nombre de la imagen
     */
    public void convertBytesToImg(byte[] bytes, String nombreImg){
        //Se crea un ByteArrayInputStream a partir del array de bytes. Este stream permite leer los bytes de la imagen.
        //Luego se crea un ImageInputStream a partir del ByteArrayInputStream. Este stream es necesario para que el
        //ImageReader pueda leer la imagen.
        try(ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ImageInputStream iis = ImageIO.createImageInputStream(bis)){
            //Se obtiene un iterador de ImageReader que pueden leer imágenes en formato "jpg".
            Iterator<?> readers = ImageIO.getImageReadersByFormatName("jpg");
            //comprobamos antes de utilizar el ImageReader que haya lectores disponibles
            if (!readers.hasNext()) {
                throw new IOException("No ImageReaders found for format jpg");
            }
            //Luego se obtiene el primer ImageReader del iterador.
            ImageReader reader = (ImageReader) readers.next();
            //Se configura el ImageReader con el ImageInputStream y se obtiene el parámetro de lectura por defecto.
            reader.setInput(iis, true);
            ImageReadParam param = reader.getDefaultReadParam();
            //Se lee la imagen desde el stream utilizando el ImageReader.
            Image image = reader.read(0, param);
            //Se crea un BufferedImage con las dimensiones de la imagen leída y el tipo de imagen RGB.
            BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
            //Se obtiene un Graphics2D del BufferedImage y se dibuja la imagen en él.
            Graphics2D g2 = bufferedImage.createGraphics();
            g2.drawImage(image, null, null);
            //liberamos Graphics2D
            g2.dispose();
            //Se crea un objeto File para representar el archivo donde se guardará la imagen.
            File imageFile = new File(System.getProperty("user.dir")+"/src/main/resources/org/project/protectora/img/animal/"+nombreImg+".jpg");
            //Se escribe el BufferedImage en el archivo en formato "jpg".
            ImageIO.write(bufferedImage, "jpg", imageFile);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Metodo que busca en la base de datos en la tabla y campo que le digamos el valor que le digamos
     * @param tabla la tabla insertada por parametro donde buscar
     * @param campo el campo que buscar
     * @param valor el valor del campo a buscar
     * @return la entidad que coincida con los valores introducidos
     */
    public List<Entidad> buscarEntidadesPorTablaYCampo(String tabla, String campo, String valor){
        List<Entidad> entidades = new ArrayList<>();
        try{
            String query = "select * from "+tabla+" where "+campo+" like ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, valor);
            ResultSet rs = ps.executeQuery();
            if(Objects.equals(tabla, "animal"))entidades.addAll(createAnimalFromSelect(rs));
            if(Objects.equals(tabla, "usuario"))entidades.addAll(createUserFromSelect(rs));
            if(Objects.equals(tabla, "solicitudAdopcion"))entidades.addAll(createAdoptionApplicationFromSelect(rs));
        }catch (Exception e){
            e.printStackTrace();
        }
        return entidades;
    }

    /**
     * Metodo que extrae una lista de animales segun el resultado de una consulta ya realizada que entra por parametro
     * como objeto tipo ResultSet
     * @param rs el objeto tipo resultset
     * @return una lista de entidades obtenida de la consulta que entra por parametro
     * @throws SQLException excepcion que puede generar al extraer los datos de la consulta
     */
    private List<Entidad> createAnimalFromSelect(ResultSet rs) throws SQLException {
        List<Entidad> entidades = new ArrayList<>();
        while(rs.next()){
            Entidad entidad;
            String id = rs.getString("id"), nombre = rs.getString("nombre"),
                    color = rs.getString("color"), sexo = rs.getString("sexo");
            LocalDate fechaNacimiento = rs.getDate("fechaNacimiento").toLocalDate(),
                    fechaEntradaProtectora = rs.getDate("fechaEntradaProtectora").toLocalDate();
            boolean castrado = rs.getBoolean("castrado");
            long chip = rs.getLong("chip");
            byte[] img = rs.getBytes("img");

            if(rs.getString("tipo").equals("Gato")){
                entidad = new Gato(id, nombre, Color.dictionary(color.replace("-", " ")),
                        Sexo.dictionary(sexo), fechaNacimiento, fechaEntradaProtectora, castrado,
                        chip, RazaGato.dictionary(rs.getString("raza")),
                        Tamanio.dictionary(rs.getString("tamanio")), img);
            }else if(rs.getString("tipo").equals("Perro")){
                entidad = new Perro(id, nombre, Color.dictionary(color.replace("-", " ")),
                        Sexo.dictionary(sexo), fechaNacimiento, fechaEntradaProtectora, castrado,
                        chip, RazaPerro.dictionary(rs.getString("raza")),
                        Tamanio.dictionary(rs.getString("tamanio")), img);
            }else {
                entidad = new Otro(id, nombre, Color.dictionary(color.replace("-", " ")),
                        Sexo.dictionary(sexo), fechaNacimiento, fechaEntradaProtectora, castrado,
                        chip, img);
            }
            if(entidad instanceof Animal){
                convertBytesToImg(((Animal) entidad).getImg(), entidad.getId());
            }
            entidades.add(entidad);
        }
        return entidades;
    }

    /**
     * Metodo que extrae una lista de usuarios segun el resultado de una consulta ya realizada que entra por parametro
     * como objeto tipo ResultSet
     * @param rs el objeto tipo resultset
     * @return una lista de entidades obtenida de la consulta que entra por parametro
     * @throws SQLException excepcion que puede generar al extraer los datos de la consulta
     */
    private List<Entidad> createUserFromSelect(ResultSet rs) throws SQLException {
        List<Entidad> entidades = new ArrayList<>();
        while (rs.next()){
            entidades.add(
                    new Usuario(rs.getString("id"), rs.getString("email"),
                            rs.getInt("telefono"), rs.getString("nombre"),
                            rs.getDate("fechaNacimiento").toLocalDate(), rs.getString("dni"),
                            rs.getString("ocupacion"), rs.getString("direccion"),
                            rs.getString("localidad"), rs.getString("provincia"),
                            rs.getInt("codigoPostal"))
            );
        }
        return entidades;
    }
    /**
     * Metodo que extrae una lista de solicitudes segun el resultado de una consulta ya realizada que entra por parametro
     * como objeto tipo ResultSet
     * @param rs el objeto tipo resultset
     * @return una lista de entidades obtenida de la consulta que entra por parametro
     * @throws SQLException excepcion que puede generar al extraer los datos de la consulta
     */
    private List<Entidad> createAdoptionApplicationFromSelect(ResultSet rs) throws SQLException {
        List<Entidad> entidades = new ArrayList<>();
        while(rs.next()){
            entidades.add(
                    new SolicitudAdopcion(rs.getString("id"),
                            buscarAnimalPorId(rs.getString("animal")),
                            buscarUsuarioPorId(rs.getString("adoptante")))
            );
        }
        return entidades;
    }
}
