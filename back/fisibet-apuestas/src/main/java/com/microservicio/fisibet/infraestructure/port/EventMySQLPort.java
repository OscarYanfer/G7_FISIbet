package com.microservicio.fisibet.infraestructure.port;

import com.microservicio.fisibet.aplication.port.EventPort;
import com.microservicio.fisibet.domain.entity.EventEntity;
import com.microservicio.fisibet.infraestructure.mapper.EventInfraMapper;
import com.microservicio.fisibet.infraestructure.model.*;
import com.microservicio.fisibet.infraestructure.port.spring.EventSpringPort;
import com.microservicio.fisibet.infraestructure.port.spring.TicketBetSpringPort;
import com.microservicio.fisibet.infraestructure.port.spring.TicketSpringPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class EventMySQLPort implements EventPort {
    @Autowired
    private EventSpringPort eventSpringPort;
    @Autowired
    private EventInfraMapper eventInfraMapper;
    @Autowired
    private TicketBetSpringPort ticketBetSpringPort;
    @Autowired
    private TicketSpringPort ticketSpringPort;
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public List<EventEntity> GetEventsEnabled() {
        List<EventModel> eventModels = this.eventSpringPort.getEventsEnabled();
        return this.eventInfraMapper.convertEventModelsToEventEntities(eventModels);
    }

    @Override
    public EventEntity registerEvent(EventEntity eventEntity) {
        eventEntity.setStatus(1);
        eventEntity.setRegisteredOn(LocalDateTime.now());
        EventModel eventModel = this.eventInfraMapper.convertEventEntityToEventModel(eventEntity);
        EventModel newEventModel = this.eventSpringPort.save(eventModel);
        return this.eventInfraMapper.convertEventModelToEventEntity(newEventModel);
    }

    @Override
    public EventEntity updateEventById(EventEntity eventEntity, Integer eventId) {
        EventModel eventModel = this.eventSpringPort.findById(eventId).orElse(null);
        eventModel.setEquipoA(eventEntity.getEquipoA());
        eventModel.setEquipoB(eventEntity.getEquipoB());
        eventModel.setFechaHora(eventEntity.getFechaHora());
        eventModel.setLiga(eventEntity.getLiga());
        eventModel.setUpdatedOn(LocalDateTime.now());
        eventModel.setStatus(1);
        return this.eventInfraMapper.convertEventModelToEventEntity(this.eventSpringPort.save(eventModel));
    }

    @Override
    public EventEntity updateStateEventById(Integer eventId, Integer state) {
        EventModel eventModel = this.eventSpringPort.findById(eventId).orElse(null);
        eventModel.setUpdatedOn(LocalDateTime.now());
        eventModel.setStatus(state);

        if(state == 3){
            List<TicketBetModel> ticketBetModels = this.ticketBetSpringPort.getTicketBetsByBetId(eventId);
            for (TicketBetModel item : ticketBetModels) {
                item.setStatus(state);
                item.setUpdatedOn(LocalDateTime.now());
                TicketBetModel ticketBetModel = this.ticketBetSpringPort.save(item);
            }

            List<TicketModel> ticketModels = this.ticketSpringPort.getTicketsByEventId(eventId);
            for (TicketModel item : ticketModels) {
                List<Integer> status = new ArrayList<Integer>();
                for (TicketBetModel x: item.getTicketBets()) {
                    status.add(x.getStatus());
                }

                Boolean isEqual = this.validEqualsElementsList(status);

                if(isEqual){
                    item.setStatus(state);
                    item.setUpdatedOn(LocalDateTime.now());
                    TicketModel ticketModelNew = this.ticketSpringPort.save(item);
                    PayEvent payEvent = new PayEvent();
                    payEvent.setEventType("Pagar");
                    payEvent.setAccountId(item.getIdAccountUser());
                    payEvent.setTotalFee(item.getTotalFee());
                    this.kafkaTemplate.send("pay-topic", payEvent);
                }
            }
        }

        return this.eventInfraMapper.convertEventModelToEventEntity(this.eventSpringPort.save(eventModel));
    }

    @Override
    public EventEntity getEventById(Integer id) {
        EventModel eventModel = this.eventSpringPort.findById(id).orElseThrow(
                () -> new RuntimeException(String.format("Evento %s no encontrado",id))
        );
        return this.eventInfraMapper.convertEventModelToEventEntity(eventModel);
    }

    public boolean validEqualsElementsList(List<Integer> arr){
        for (int i = 0; i < arr.size(); i++) {
            if(!arr.get(i).equals(3)){
                return false;
            }
        }
        return true;
    }
}
