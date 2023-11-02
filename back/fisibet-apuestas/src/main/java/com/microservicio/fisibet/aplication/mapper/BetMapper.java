package com.microservicio.fisibet.aplication.mapper;

import com.microservicio.fisibet.aplication.dto.CreateBetDto;
import com.microservicio.fisibet.aplication.dto.GetBetDto;
import com.microservicio.fisibet.aplication.request.CreateBetRequest;
import com.microservicio.fisibet.aplication.response.CreateBetResponse;
import com.microservicio.fisibet.aplication.response.GetBetResponse;
import com.microservicio.fisibet.domain.entity.BetEntity;
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
