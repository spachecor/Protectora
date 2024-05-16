package org.project.protectora.servicios.logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.*;

/**
 * Clase de utilidad para construir un logger a partir del nombre de la clase que lo requiere
 * @author selene
 * @version 1.1
 */
public class ProtectoraLogger {
    /**
     * Método estático que general el logger personalizado a través del nombre de la clase que lo solicita
     * @param clase Es el nombre de la clase que utiliza el método
     * @return el objeto de tipo logger listo y configurado para usarlo
     */
    public static Logger getLogger(String clase){
        Logger logger = Logger.getLogger(clase);
        try {
            //tomamos un nuevo objeto LogManager, luego reiniciamos las propiedades de registro y leemos la configuración de registro en nuestro fichero protectoraLogging.propierties
            LogManager.getLogManager().readConfiguration(new FileInputStream(System.getProperty("user.dir")+"/src/protectoraLogging.properties"));
        }catch(SecurityException | IOException e) {
            //imprimimos la pila si se captura una excepcion
            e.printStackTrace();
        }
        //asignamos el nivel
        logger.setLevel(Level.FINE);
        //creamos el manejador de consola
        ConsoleHandler consoleHandler = new ConsoleHandler();
        //agregamos le formato al consoleHandler
        consoleHandler.setFormatter(new ProtectoraFormatter());
        //añadimos el consolehandler
        logger.addHandler(consoleHandler);
        //añadimos el manejador personalizado
        logger.addHandler(new ProtectoraHandler());
        try {
            //nuevo filehandler para fichero con limitación de tamaño y numero de registros de mensajes
            Handler fileHandler = new FileHandler(System.getProperty("user.dir")+"/logs/logger.log", 5000000, 5);
            //le asignamos un nuevo formato
            fileHandler.setFormatter(new ProtectoraFormatter());
            //le asignamos el filtro
            fileHandler.setFilter(new ProtectoraFilter());
            //agregamos nuestro fileHandler al logger
            logger.addHandler(fileHandler);
        }catch(SecurityException|IOException e) {
            e.printStackTrace();
        }
        return logger;
    }
}
