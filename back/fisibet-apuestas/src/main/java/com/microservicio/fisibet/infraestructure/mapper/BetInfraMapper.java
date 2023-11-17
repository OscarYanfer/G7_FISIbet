package com.microservicio.fisibet.infraestructure.mapper;

import com.microservicio.fisibet.aplication.mapper.BetMapper;
import com.microservicio.fisibet.domain.entity.BetEntity;
import com.microservicio.fisibet.infraestructure.model.BetModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BetInfraMapper {
    BetModel convertBetEntityToBetModel(BetEntity betEntity);
    BetEntity convertBetModelToBetEntity(BetModel betModel);
    List<BetEntity> convertBetModelsToBetEntities(List<BetModel> betModels);
}
