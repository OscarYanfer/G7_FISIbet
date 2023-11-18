package com.microservicio.fisibet.aplication.port;

import com.microservicio.fisibet.domain.entity.AccountUserEntity;
import com.microservicio.fisibet.domain.entity.AccountUserEventEntity;

import java.util.List;

public interface AccountUserPort {
    AccountUserEntity createAccountUser(AccountUserEntity accountUserEntity);
    AccountUserEntity getAccountUserById(Integer id);
    List<AccountUserEntity> getAccountUsers();
    AccountUserEntity updateAccountUserById(AccountUserEntity accountUserEntity, Integer id);
}
