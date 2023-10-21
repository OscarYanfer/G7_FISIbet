package com.ms.gestionapuestas.aplication.mapper;
import com.ms.gestionapuestas.aplication.dto.CreateEventDto;
import com.ms.gestionapuestas.aplication.dto.GetEventDto;
import com.ms.gestionapuestas.aplication.request.CreateEventRequest;
import com.ms.gestionapuestas.aplication.response.CreateEventResponse;
import com.ms.gestionapuestas.aplication.response.GetEventResponse;
import com.ms.gestionapuestas.domain.entity.EventEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {
    List<GetEventResponse> convertGetEventDtoToGetEventResponse(List<GetEventDto> getEventDtos);
    List<GetEventDto> convertEventEntitiesToGetEventDto(List<EventEntity> eventEntities);
    CreateEventDto convertCreateEventRequestToCreateEventDto(CreateEventRequest createEventRequest);
    CreateEventResponse convertCreateEventDtoToCreateEventResponse(CreateEventDto createEventDto);
    EventEntity convertCreateEventDtoToEventEntity(CreateEventDto createEventDto);
}
