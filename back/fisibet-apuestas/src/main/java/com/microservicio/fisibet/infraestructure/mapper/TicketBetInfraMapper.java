package com.microservicio.fisibet.infraestructure.mapper;

import com.microservicio.fisibet.domain.entity.TicketBetEntity;
import com.microservicio.fisibet.infraestructure.model.TicketBetModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TicketBetInfraMapper {
    TicketBetModel convertTicketBetEntityToTicketBetModel(TicketBetEntity ticketBetEntity);
    TicketBetEntity convertTicketBetModelToTicketBetEntity(TicketBetModel ticketBetModel);
}
