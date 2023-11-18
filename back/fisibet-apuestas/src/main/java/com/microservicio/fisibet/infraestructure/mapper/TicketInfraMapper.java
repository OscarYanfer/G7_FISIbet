package com.microservicio.fisibet.infraestructure.mapper;

import com.microservicio.fisibet.domain.entity.TicketEntity;
import com.microservicio.fisibet.infraestructure.model.TicketModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TicketInfraMapper {
    TicketEntity convertTicketModelToTicketEntity(TicketModel ticketModel);
    TicketModel convertTicketEntityToTicketModel(TicketEntity ticketEntity);
    List<TicketEntity> convertTicketModelsToTicketEntities(List<TicketModel> ticketModels);
}
