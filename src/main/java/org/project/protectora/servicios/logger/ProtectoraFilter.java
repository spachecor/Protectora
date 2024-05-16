package org.project.protectora.servicios.logger;

import java.util.logging.Filter;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * Clase de utilidad que contiene un filtro personalizado para el logger
 * @author selene
 * @version 1.0
 */
public class ProtectoraFilter implements Filter {
    @Override
    public boolean isLoggable(LogRecord record) {
        //se filtran los niveles de las depuraciones, si es FINEST, no se hace
        if(record.getLevel()==Level.FINEST)return false;
        return true;
    }
}
