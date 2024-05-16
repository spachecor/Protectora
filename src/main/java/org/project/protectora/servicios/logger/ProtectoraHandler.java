package org.project.protectora.servicios.logger;

import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;
/**
 * Clase de utilidad que contiene un manejador personalizado para el logger
 * @author selene
 * @version 1.0
 */
public class ProtectoraHandler extends StreamHandler {
    @Override
    public void publish(LogRecord record) {
        super.publish(record);
    }

    @Override
    public void flush() {
        super.flush();
    }

    @Override
    public void close() throws SecurityException {
        super.close();
    }
}
