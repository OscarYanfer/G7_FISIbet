package com.microservicio.fisibet.aplication.port;

import com.microservicio.fisibet.domain.entity.TicketBetEntity;

public interface TicketBetPort {
    Integer createTicketBet(TicketBetEntity ticketBetEntity);
}
