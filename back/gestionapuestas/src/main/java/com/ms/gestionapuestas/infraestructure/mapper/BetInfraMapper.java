package com.ms.gestionapuestas.infraestructure.mapper;

import com.ms.gestionapuestas.domain.entity.BetEntity;
import com.ms.gestionapuestas.infraestructure.model.BetModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BetInfraMapper {
    BetModel convertBetEntityToBetModel(BetEntity betEntity);
    List<BetEntity> convertBetModelsToBetEntities(List<BetModel> betModels);
}
