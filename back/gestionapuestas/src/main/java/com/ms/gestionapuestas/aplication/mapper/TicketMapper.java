package com.ms.gestionapuestas.aplication.mapper;

import com.ms.gestionapuestas.aplication.dto.CreateTicketDto;
import com.ms.gestionapuestas.aplication.request.CreateTicketRequest;
import com.ms.gestionapuestas.aplication.response.CreateTicketResponse;
import com.ms.gestionapuestas.domain.entity.TicketEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    CreateTicketDto convertCreateTicketRequestToCreateTicketDto(CreateTicketRequest createTicketRequest);
    CreateTicketResponse convertCreateTicketDtoToCreateTicketResponse(CreateTicketDto createTicketDto);
    TicketEntity convertCreateTicketDtoToTicketEntity(CreateTicketDto createTicketDto);

    CreateTicketDto convertTicketEntityToCreateTicketDto(TicketEntity ticketEntity);
}
