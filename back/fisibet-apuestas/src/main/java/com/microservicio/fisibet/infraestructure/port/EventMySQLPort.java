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
    public Integer registerEvent(EventEntity eventEntity) {
        eventEntity.setStatus(1);
        eventEntity.setRegistered(LocalDateTime.now());
        EventModel eventModel = this.eventInfraMapper.convetEventEntityToEventModel(eventEntity);
        EventModel newEventModel = this.eventSpringPort.save(eventModel);
        return newEventModel.getId();
    }
}
