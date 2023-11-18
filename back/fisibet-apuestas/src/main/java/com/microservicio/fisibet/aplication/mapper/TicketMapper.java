package com.microservicio.fisibet.aplication.mapper;

import com.microservicio.fisibet.aplication.dto.CreateTicketDto;
import com.microservicio.fisibet.aplication.dto.TicketDto;
import com.microservicio.fisibet.aplication.request.CreateTicketRequest;
import com.microservicio.fisibet.aplication.response.CreateTicketResponse;
import com.microservicio.fisibet.aplication.response.TicketResponse;
import com.microservicio.fisibet.domain.entity.TicketEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    CreateTicketDto convertCreateTicketRequestToCreateTicketDto(CreateTicketRequest createTicketRequest);
    CreateTicketResponse convertCreateTicketDtoToCreateTicketResponse(CreateTicketDto createTicketDto);
    TicketEntity convertCreateTicketDtoToTicketEntity(CreateTicketDto createTicketDto);
    CreateTicketDto convertTicketEntityToCreateTicketDto(TicketEntity ticketEntity);
    TicketDto convertTicketEntityToTicketDto(TicketEntity ticketEntity);
    TicketResponse convertTicketDtoToTicketResponse(TicketDto ticketDto);
    List<TicketDto> convertTicketEntitiesToTicketDtos(List<TicketEntity> ticketEntities);
    List<TicketResponse> convertTicketDtosToTicketResponses(List<TicketDto> ticketDtos);
}

