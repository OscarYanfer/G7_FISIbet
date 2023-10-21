package com.ms.gestionapuestas.aplication.mapper;

import com.ms.gestionapuestas.aplication.dto.CreateTicketBetDto;
import com.ms.gestionapuestas.domain.entity.TicketBetEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TicketBetMapper {
    TicketBetEntity convertCreateTicketBetDtoToTicketBetEntity(CreateTicketBetDto createTicketBetDto);
}
