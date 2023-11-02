package com.microservicio.fisibet.aplication.port;

import com.microservicio.fisibet.domain.entity.AccountUserEntity;
import com.microservicio.fisibet.domain.entity.AccountUserEventEntity;

public interface AccountUserPort {
    AccountUserEntity createAccountUser(AccountUserEventEntity accountUserEvent);
}
