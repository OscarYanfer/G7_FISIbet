package com.microservicio.fisibet.aplication.mapper;

import com.microservicio.fisibet.aplication.dto.CardDto;
import com.microservicio.fisibet.aplication.dto.CreateCardDto;
import com.microservicio.fisibet.aplication.request.CreateCardRequest;
import com.microservicio.fisibet.aplication.response.CardResponse;
import com.microservicio.fisibet.domain.entity.CardEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardMapper {
    CreateCardDto convertCreateCardRequestToCreateCardDto(CreateCardRequest createCardRequest);
    CardResponse convertCardDtoToCardResponse(CardDto cardDto);
    CardEntity convertCreateCardDtoToCardEntity(CreateCardDto createCardDto);
    CardDto convertCardEntityToCardDto(CardEntity cardEntity);
}
