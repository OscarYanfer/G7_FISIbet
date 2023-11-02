package com.microservicio.fisibet.aplication.mapper;

import com.microservicio.fisibet.aplication.dto.CreateTicketBetDto;
import com.microservicio.fisibet.domain.entity.TicketBetEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TicketBetMapper {
    TicketBetEntity convertCreateTicketBetDtoToTicketBetEntity(CreateTicketBetDto createTicketBetDto);
}
