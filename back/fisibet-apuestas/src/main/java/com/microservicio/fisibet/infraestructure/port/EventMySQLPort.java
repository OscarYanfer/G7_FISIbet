package com.microservicio.fisibet.infraestructure.port;

import com.microservicio.fisibet.aplication.port.EventPort;
import com.microservicio.fisibet.domain.entity.EventEntity;
import com.microservicio.fisibet.infraestructure.mapper.EventInfraMapper;
import com.microservicio.fisibet.infraestructure.model.EventModel;
import com.microservicio.fisibet.infraestructure.port.spring.EventSpringPort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventMySQLPort implements EventPort {
    private EventSpringPort eventSpringPort;
    private EventInfraMapper eventInfraMapper;
    public EventMySQLPort(EventSpringPort eventSpringPort,
                          EventInfraMapper eventInfraMapper){
        this.eventSpringPort = eventSpringPort;
        this.eventInfraMapper = eventInfraMapper;
    }
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
    public EventEntity getEventById(Integer id) {
        EventModel eventModel = this.eventSpringPort.findById(id).orElseThrow(
                () -> new RuntimeException(String.format("Evento %s no encontrado",id))
        );
        return this.eventInfraMapper.convertEventModelToEventEntity(eventModel);
    }
}
