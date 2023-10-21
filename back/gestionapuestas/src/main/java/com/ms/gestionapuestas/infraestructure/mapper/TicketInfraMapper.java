package com.ms.gestionapuestas.infraestructure.mapper;

import com.ms.gestionapuestas.domain.entity.TicketEntity;
import com.ms.gestionapuestas.infraestructure.model.TicketModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TicketInfraMapper {
    TicketEntity convertTicketModelToTicketEntity(TicketModel ticketModel);
    TicketModel convertTicketEntityToTicketModel(TicketEntity ticketEntity);
}
