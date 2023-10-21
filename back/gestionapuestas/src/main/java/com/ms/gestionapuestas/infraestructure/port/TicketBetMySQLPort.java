package com.ms.gestionapuestas.infraestructure.port;

import com.ms.gestionapuestas.aplication.port.TicketBetPort;
import com.ms.gestionapuestas.domain.entity.TicketBetEntity;
import com.ms.gestionapuestas.infraestructure.mapper.TicketBetInfraMapper;
import com.ms.gestionapuestas.infraestructure.model.TicketBetModel;
import com.ms.gestionapuestas.infraestructure.port.spring.TicketBetSpringPort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TicketBetMySQLPort implements TicketBetPort {
    private TicketBetInfraMapper ticketBetInfraMapper;
    private TicketBetSpringPort ticketBetSpringPort;
    public TicketBetMySQLPort(
            TicketBetInfraMapper ticketBetInfraMapper,
            TicketBetSpringPort ticketBetSpringPort
    ){
        this.ticketBetInfraMapper = ticketBetInfraMapper;
        this.ticketBetSpringPort = ticketBetSpringPort;
    }

    @Override
    public Integer createTicketBet(TicketBetEntity ticketBetEntity) {
        ticketBetEntity.setStatus(1);
        ticketBetEntity.setRegistered(LocalDateTime.now());
        TicketBetModel ticketBetModel = this.ticketBetInfraMapper.convertTicketBetEntityToTicketBetModel(ticketBetEntity);
        TicketBetModel ticketBetModeNew = this.ticketBetSpringPort.save(ticketBetModel);
        return ticketBetModeNew.getId();
    }
}
