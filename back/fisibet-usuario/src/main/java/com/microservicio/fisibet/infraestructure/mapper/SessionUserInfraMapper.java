package com.microservicio.fisibet.infraestructure.mapper;

import com.microservicio.fisibet.domain.entity.SessionUserEntity;
import com.microservicio.fisibet.infraestructure.model.AccountUserModel;
import com.microservicio.fisibet.infraestructure.model.SessionUserModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SessionUserInfraMapper {
    SessionUserModel convertSessionUserEntityToSessionUserModel(SessionUserEntity sessionUserEntity);
    SessionUserEntity convertSessionUserModelToSessionUserEntity(SessionUserModel sessionUserModel);
    SessionUserModel convertAccountUserModelToSessionUserModel(AccountUserModel accountUserModel);
}
