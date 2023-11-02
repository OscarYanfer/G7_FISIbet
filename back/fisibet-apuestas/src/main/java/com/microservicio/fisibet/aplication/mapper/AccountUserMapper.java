package com.microservicio.fisibet.aplication.mapper;

import com.microservicio.fisibet.aplication.dto.AccountUserDto;
import com.microservicio.fisibet.aplication.response.AccountUserResponse;
import com.microservicio.fisibet.domain.entity.AccountUserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountUserMapper {
    AccountUserResponse convertAccountUserDtoToAccountUserResponse(AccountUserDto accountUserDto);
    AccountUserDto convertAccountUserResponseToAccountUserDto(AccountUserResponse accountUserResponse);
    List<AccountUserResponse> convertAccountUserDtosToAccountUserResponses(List<AccountUserDto> accountUserDtos);
    List<AccountUserDto> convertAccountUserResponsesToAccountUserDtos(List<AccountUserResponse> accountUserResponses);
    List<AccountUserDto> convertAccountUserEntitiesToAccountUserDtos(List<AccountUserEntity> accountUserEntities);
}
