package com.microservicio.fisibet.aplication.mapper;
import com.microservicio.fisibet.aplication.dto.CreateEventDto;
import com.microservicio.fisibet.aplication.dto.GetEventDto;
import com.microservicio.fisibet.aplication.request.CreateEventRequest;
import com.microservicio.fisibet.aplication.response.CreateEventResponse;
import com.microservicio.fisibet.aplication.response.GetEventResponse;
import com.microservicio.fisibet.domain.entity.EventEntity;
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
