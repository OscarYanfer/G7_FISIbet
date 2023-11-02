package com.microservicio.fisibet.aplication.port;

import com.microservicio.fisibet.domain.entity.TicketEntity;

public interface TicketPort {
    TicketEntity getTicketById(Integer ticketId);
}
