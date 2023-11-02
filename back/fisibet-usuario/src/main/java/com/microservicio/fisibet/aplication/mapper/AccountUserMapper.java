package com.microservicio.fisibet.aplication.mapper;

import com.microservicio.fisibet.aplication.dto.AccountUserDto;
import com.microservicio.fisibet.aplication.dto.CreateAccountUserDto;
import com.microservicio.fisibet.aplication.request.CreateAccountUserRequest;
import com.microservicio.fisibet.aplication.response.CreateAccountUserResponse;
import com.microservicio.fisibet.domain.entity.AccountUserEntity;
import com.microservicio.fisibet.domain.entity.AccountUserEventEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface AccountUserMapper {
    @Mappings({
            @Mapping(source="accountUser", target="accountUser")
    })
    CreateAccountUserDto convertCreateAccountUserRequestToCreateAccountUserDto(CreateAccountUserRequest createAccountUserRequest);
    CreateAccountUserResponse convertCreateAccountUserDtoToCreateAccountUserResponse(CreateAccountUserDto createAccountUserDto);
    AccountUserEntity convertCreateAccountUserDtoToAccountUserEntity(CreateAccountUserDto createAccountUserDto);
    CreateAccountUserDto convertAccountUserEntityToCreateAccountUserDto(AccountUserEntity accountUserEntity);
    AccountUserEventEntity convertCreateAccountUserToAccountUserEventEntity(CreateAccountUserDto createAccountUserDto);
    AccountUserDto convertAccountUserEntityToAccountUserDto(AccountUserEntity accountUserEntity);
    CreateAccountUserResponse convertAccountUserDtoCreateAccountUserResponse(AccountUserDto accountUserDto);
}
