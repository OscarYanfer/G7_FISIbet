package com.ms.gestionapuestas.aplication.port;

import com.ms.gestionapuestas.domain.entity.TicketBetEntity;

public interface TicketBetPort {
    Integer createTicketBet(TicketBetEntity ticketBetEntity);
}
