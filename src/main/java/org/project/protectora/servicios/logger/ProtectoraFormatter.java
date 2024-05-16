package org.project.protectora.servicios.logger;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * Clase de utilidad que contiene un formato personalizado para el logger
 * @author selene
 * @version 1.1
 */
public class ProtectoraFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        return "Ubicación: "+record.getSourceClassName()+" - Método: "+record.getSourceMethodName()+" - Instante en el que ocurre: "
                +new Date(record.getMillis())+" - Mensaje: "+record.getMessage()+"\n";
    }
}
