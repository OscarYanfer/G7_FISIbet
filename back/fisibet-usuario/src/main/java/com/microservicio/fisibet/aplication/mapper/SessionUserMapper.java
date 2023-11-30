package com.microservicio.fisibet.aplication.mapper;

import com.microservicio.fisibet.aplication.dto.AuthDto;
import com.microservicio.fisibet.aplication.request.AuthRequest;
import com.microservicio.fisibet.aplication.response.AuthResponse;
import com.microservicio.fisibet.domain.entity.SessionUserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SessionUserMapper {
    AuthDto convertAuthRequestToAuthDto(AuthRequest authRequest);
    AuthResponse convertAuthDtoToAuthResponse(AuthDto authDto);
    SessionUserEntity convertAuthDtoToSessionUserEntity(AuthDto authDto);
    AuthDto convertSessionUserEntityToAuthDto(SessionUserEntity sessionUserEntity);

}
