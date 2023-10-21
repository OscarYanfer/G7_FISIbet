package com.ms.gestionapuestas.aplication.mapper;

import com.ms.gestionapuestas.aplication.dto.CreateBetDto;
import com.ms.gestionapuestas.aplication.dto.CreateEventDto;
import com.ms.gestionapuestas.aplication.dto.GetBetDto;
import com.ms.gestionapuestas.aplication.request.CreateBetRequest;
import com.ms.gestionapuestas.aplication.response.CreateBetResponse;
import com.ms.gestionapuestas.aplication.response.GetBetResponse;
import com.ms.gestionapuestas.domain.entity.BetEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BetMapper {
    CreateBetDto convertCreateBetRequestToCreateBetDto(CreateBetRequest request);
    BetEntity convertCreateBetDtoToBetEntity(CreateBetDto createBetDto);
    CreateBetResponse convertCreateBetDtoToCreateBetResponse(CreateBetDto createBetDto);
    List<GetBetDto> convertBetEntitiesToGetBetDtos(List<BetEntity> betEntities);

    List<GetBetResponse> convertGetBetDtosToGetBetResponses(List<GetBetDto> betDtos);

}
