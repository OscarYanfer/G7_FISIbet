package com.ms.gestionapuestas.infraestructure.mapper;

import com.ms.gestionapuestas.domain.entity.EventEntity;
import com.ms.gestionapuestas.infraestructure.model.EventModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventInfraMapper {
    List<EventEntity> convertEventModelsToEventEntities(List<EventModel> eventModels);
    EventModel convetEventEntityToEventModel(EventEntity eventEntity);
}
