package com.ms.gestionapuestas.infraestructure.mapper;

import com.ms.gestionapuestas.domain.entity.TicketBetEntity;
import com.ms.gestionapuestas.infraestructure.model.TicketBetModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TicketBetInfraMapper {
    TicketBetModel convertTicketBetEntityToTicketBetModel(TicketBetEntity ticketBetEntity);
    TicketBetEntity convertTicketBetModelToTicketBetEntity(TicketBetModel ticketBetModel);
}
