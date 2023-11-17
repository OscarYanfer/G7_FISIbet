package com.microservicio.fisibet.infraestructure.mapper;

import com.microservicio.fisibet.aplication.mapper.EventMapper;
import com.microservicio.fisibet.domain.entity.EventEntity;
import com.microservicio.fisibet.infraestructure.model.EventModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventInfraMapper {
    List<EventEntity> convertEventModelsToEventEntities(List<EventModel> eventModels);
    EventModel convertEventEntityToEventModel(EventEntity eventEntity);
    EventEntity convertEventModelToEventEntity(EventModel eventModel);
}
