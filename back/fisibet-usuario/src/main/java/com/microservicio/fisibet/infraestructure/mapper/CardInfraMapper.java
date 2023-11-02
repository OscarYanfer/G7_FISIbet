package com.microservicio.fisibet.infraestructure.mapper;

import com.microservicio.fisibet.domain.entity.CardEntity;
import com.microservicio.fisibet.infraestructure.model.CardModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardInfraMapper {
    CardEntity convertoCardModelToCardEntity(CardModel cardModel);
    CardModel convertCardEntityToCardModel(CardEntity cardEntity);
}
