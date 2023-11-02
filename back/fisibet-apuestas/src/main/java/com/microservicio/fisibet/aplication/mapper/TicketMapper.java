package com.microservicio.fisibet.aplication.mapper;

import com.microservicio.fisibet.aplication.dto.CreateTicketDto;
import com.microservicio.fisibet.aplication.request.CreateTicketRequest;
import com.microservicio.fisibet.aplication.response.CreateTicketResponse;
import com.microservicio.fisibet.domain.entity.TicketEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    CreateTicketDto convertCreateTicketRequestToCreateTicketDto(CreateTicketRequest createTicketRequest);
    CreateTicketResponse convertCreateTicketDtoToCreateTicketResponse(CreateTicketDto createTicketDto);
    TicketEntity convertCreateTicketDtoToTicketEntity(CreateTicketDto createTicketDto);

    CreateTicketDto convertTicketEntityToCreateTicketDto(TicketEntity ticketEntity);
}
