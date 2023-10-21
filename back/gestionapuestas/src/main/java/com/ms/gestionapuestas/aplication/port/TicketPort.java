package com.ms.gestionapuestas.aplication.port;

import com.ms.gestionapuestas.aplication.dto.CreateTicketDto;
import com.ms.gestionapuestas.domain.entity.TicketEntity;

public interface TicketPort {
    TicketEntity createTicket(TicketEntity ticketEntity);
}
