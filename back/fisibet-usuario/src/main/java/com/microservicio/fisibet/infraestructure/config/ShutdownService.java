package com.microservicio.fisibet.infraestructure.config;

import com.microservicio.fisibet.infraestructure.port.SessionUserMySQLPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import javax.persistence.Access;

@Component
public class ShutdownService implements ApplicationListener<ContextClosedEvent> {
    @Autowired
    private SessionUserMySQLPort sessionUserMySQLPort;
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        // Código a ejecutar al cerrar la aplicación
        System.out.println("La aplicación se está cerrando. Ejecutando servicio de cierre.");

        this.sessionUserMySQLPort.disconnectUsers();

        // Agrega aquí la lógica que deseas ejecutar al cerrar la aplicación.
    }
}