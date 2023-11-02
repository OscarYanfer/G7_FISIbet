package com.microservicio.fisibet.infraestructure.port;

import com.microservicio.fisibet.aplication.port.TicketBetPort;
import com.microservicio.fisibet.domain.entity.TicketBetEntity;
import com.microservicio.fisibet.infraestructure.mapper.TicketBetInfraMapper;
import com.microservicio.fisibet.infraestructure.model.TicketBetModel;
import com.microservicio.fisibet.infraestructure.port.spring.TicketBetSpringPort;
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
