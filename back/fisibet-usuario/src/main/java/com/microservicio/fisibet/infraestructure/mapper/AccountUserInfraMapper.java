package com.microservicio.fisibet.infraestructure.mapper;

import com.microservicio.fisibet.domain.entity.AccountUserEntity;
import com.microservicio.fisibet.domain.entity.AccountUserEventEntity;
import com.microservicio.fisibet.infraestructure.model.AccountUserEvent;
import com.microservicio.fisibet.infraestructure.model.AccountUserModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountUserInfraMapper {
    AccountUserModel convertAccountUserEntityToAccountUserModel(AccountUserEntity accountUserEntity);
    AccountUserEntity convertAccountUserModelToAccountUserEntity(AccountUserModel accountUserModel);
    AccountUserEvent convertAccountUserEventEntityToAccountUserEvent(AccountUserEventEntity accountUserEventEntity);
    List<AccountUserEntity> convertAccountUserModelsToAccountUserEntities(List<AccountUserModel> accountUserModels);
}
