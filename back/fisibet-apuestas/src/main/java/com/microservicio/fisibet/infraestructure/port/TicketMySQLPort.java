package com.microservicio.fisibet.infraestructure.port;

import com.microservicio.fisibet.aplication.port.TicketPort;
import com.microservicio.fisibet.domain.entity.TicketEntity;
import com.microservicio.fisibet.infraestructure.mapper.TicketInfraMapper;
import com.microservicio.fisibet.infraestructure.model.TicketModel;
import com.microservicio.fisibet.infraestructure.port.spring.TicketSpringPort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class TicketMySQLPort implements TicketPort {
    private TicketSpringPort ticketSpringPort;
    private TicketInfraMapper ticketInfraMapper;
    public TicketMySQLPort(
            TicketSpringPort ticketSpringPort,
            TicketInfraMapper ticketInfraMapper
    ){
        this.ticketInfraMapper = ticketInfraMapper;
        this.ticketSpringPort = ticketSpringPort;
    }
    @Override
    public TicketEntity createTicket(TicketEntity ticketEntity) {
        ticketEntity.setStatus(1);
        ticketEntity.setRegistered(LocalDateTime.now());
        Integer countRows = this.ticketSpringPort.getCountRows();
        ticketEntity.setNumber("TCK"+(countRows+1));
        TicketModel ticketModel = this.ticketInfraMapper.convertTicketEntityToTicketModel(ticketEntity);
        TicketModel ticketModelNew = this.ticketSpringPort.save(ticketModel);
        return this.ticketInfraMapper.convertTicketModelToTicketEntity(ticketModelNew);
    }
}
