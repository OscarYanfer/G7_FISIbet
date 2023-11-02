package com.microservicio.fisibet.infraestructure.port;

import com.microservicio.fisibet.aplication.port.TicketPort;
import com.microservicio.fisibet.domain.entity.TicketEntity;
import com.microservicio.fisibet.infraestructure.model.CardEvent;
import com.microservicio.fisibet.infraestructure.model.TicketEvent;
import com.microservicio.fisibet.infraestructure.port.spring.TicketSpringPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TicketMySQLPort implements TicketPort {
    @Autowired
    private TicketSpringPort ticketSpringPort;
    @Override
    public TicketEntity getTicketById(Integer ticketId) {
        return null;
    }

    @KafkaListener(topics = "ticket-topic", groupId = "msAbonoPago",containerFactory = "abonoPagoListener")
    public void processTicketEvents(TicketEvent ticketEvent){
        this.ticketSpringPort.save(ticketEvent.getTicket());
    }

}
