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

public class ConexionBBDD {
    private static String url, pwd, user;
    private static Connection connection;

    static {
        url = "jdbc:MySQL://localhost/protectora";
        pwd = "";
        user = "root";
    }
    public ConexionBBDD() throws SQLException {
        connection = DriverManager.getConnection(url, user, pwd);
    }
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
            while(resultSetUno.next()){
                Entidad entidad;
                String id = resultSetUno.getString("id"), nombre = resultSetUno.getString("nombre"),
                        color = resultSetUno.getString("color"), sexo = resultSetUno.getString("sexo");
                LocalDate fechaNacimiento = resultSetUno.getDate("fechaNacimiento").toLocalDate(),
                        fechaEntradaProtectora = resultSetUno.getDate("fechaEntradaProtectora").toLocalDate();
                boolean castrado = resultSetUno.getBoolean("castrado");
                long chip = resultSetUno.getLong("chip");
                byte[] img = resultSetUno.getBytes("img");

                if(resultSetUno.getString("tipo").equals("Gato")){
                    entidad = new Gato(id, nombre, Color.dictionary(color.replace("-", " ")),
                            Sexo.dictionary(sexo), fechaNacimiento, fechaEntradaProtectora, castrado,
                            chip, RazaGato.dictionary(resultSetUno.getString("raza")),
                            Tamanio.dictionary(resultSetUno.getString("tamanio")), img);
                }else if(resultSetUno.getString("tipo").equals("Perro")){
                    entidad = new Perro(id, nombre, Color.dictionary(color.replace("-", " ")),
                            Sexo.dictionary(sexo), fechaNacimiento, fechaEntradaProtectora, castrado,
                            chip, RazaPerro.dictionary(resultSetUno.getString("raza")),
                            Tamanio.dictionary(resultSetUno.getString("tamanio")), img);
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
            //ahora extraemos los usuarios
            ResultSet resultSetDos = statement.executeQuery(queryDos);
            while (resultSetDos.next()){
                entidades.add(
                        new Usuario(resultSetDos.getString("id"), resultSetDos.getString("email"),
                                resultSetDos.getInt("telefono"), resultSetDos.getString("nombre"),
                                resultSetDos.getDate("fechaNacimiento").toLocalDate(), resultSetDos.getString("dni"),
                                resultSetDos.getString("ocupacion"), resultSetDos.getString("direccion"),
                                resultSetDos.getString("localidad"), resultSetDos.getString("provincia"),
                                resultSetDos.getInt("codigoPostal"))
                );
            }
            //ahora extraemos las solicitudes de adopción
            ResultSet resultSetTres = statement.executeQuery(queryTres);
            while(resultSetTres.next()){
                entidades.add(
                        new SolicitudAdopcion(resultSetTres.getString("id"),
                                buscarAnimalPorId(resultSetTres.getString("animal")),
                                buscarUsuarioPorId(resultSetTres.getString("adoptante")))
                );
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return entidades;
    }
    private static Animal buscarAnimalPorId(String idAnimal){
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
    private static Usuario buscarUsuarioPorId(String id){
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
    private static SolicitudAdopcion buscarSolicitudPorId(String id){
        SolicitudAdopcion solicitud = null;
        try{
            String query = "SELECT * FROM usuario where id like ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                solicitud = new SolicitudAdopcion(rs.getString("id"), buscarAnimalPorId(rs.getString("animal")),
                        buscarUsuarioPorId(rs.getString("usuario")));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return solicitud;
    }

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
}
