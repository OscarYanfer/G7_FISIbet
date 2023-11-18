package com.microservicio.fisibet.aplication.port;

import com.microservicio.fisibet.domain.entity.TicketEntity;

import java.util.List;

public interface TicketPort {
    TicketEntity createTicket(TicketEntity ticketEntity);
    TicketEntity getTicketById(Integer id);
    List<TicketEntity> getTickets();
}
