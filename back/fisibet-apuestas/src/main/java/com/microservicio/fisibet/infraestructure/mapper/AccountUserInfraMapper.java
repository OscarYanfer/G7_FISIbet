package com.microservicio.fisibet.infraestructure.mapper;

import com.microservicio.fisibet.domain.entity.AccountUserEntity;
import com.microservicio.fisibet.infraestructure.model.AccountUserModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountUserInfraMapper {
    List<AccountUserEntity> convertAccountUserModelsToAccountUserEntities(List<AccountUserModel> accountUserModels);
    AccountUserModel convertAccountUserEntityToAccountUserModel(AccountUserEntity accountUserEntity);
}
