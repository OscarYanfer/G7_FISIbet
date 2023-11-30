package com.microservicio.fisibet.infraestructure.port;

import com.microservicio.fisibet.aplication.port.TicketPort;
import com.microservicio.fisibet.domain.entity.TicketEntity;
import com.microservicio.fisibet.infraestructure.mapper.TicketInfraMapper;
import com.microservicio.fisibet.infraestructure.model.TicketEvent;
import com.microservicio.fisibet.infraestructure.model.TicketModel;
import com.microservicio.fisibet.infraestructure.port.spring.TicketSpringPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketMySQLPort implements TicketPort {
    @Autowired
    private TicketSpringPort ticketSpringPort;
    @Autowired
    private TicketInfraMapper ticketInfraMapper;
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public TicketEntity createTicket(TicketEntity ticketEntity) {
        ticketEntity.setStatus(1);
        ticketEntity.setRegisteredOn(LocalDateTime.now());
        Integer countRows = this.ticketSpringPort.getCountRows();
        ticketEntity.setNumber("TCK"+(countRows+1));
        TicketModel ticketModel = this.ticketInfraMapper.convertTicketEntityToTicketModel(ticketEntity);
        TicketModel ticketModelNew = this.ticketSpringPort.save(ticketModel);
        TicketEvent ticketEvent = new TicketEvent();
        ticketEvent.setEventType("CreateTicket");
        ticketEvent.setTicket(ticketModelNew);
        this.kafkaTemplate.send("ticket-topic", ticketEvent);
        return this.ticketInfraMapper.convertTicketModelToTicketEntity(ticketModelNew);
    }

    @Override
    public TicketEntity getTicketById(Integer id) {
        //TicketModel ticketModel = this.ticketSpringPort.findById(id).orElse(null);
        TicketModel ticketModel = this.ticketSpringPort.getTicketById(id);
        return this.ticketInfraMapper.convertTicketModelToTicketEntity(ticketModel);
    }

    @Override
    public List<TicketEntity> getTickets() {
        //List<TicketModel> ticketModels = this.ticketSpringPort.findAll();
        List<TicketModel> ticketModels = this.ticketSpringPort.getTickets();
        return this.ticketInfraMapper.convertTicketModelsToTicketEntities(ticketModels);
    }
}
