package com.microservicio.fisibet.aplication.port;
import com.microservicio.fisibet.domain.entity.AccountUserEntity;

import java.util.List;

public interface AccountUserPort {
    List<AccountUserEntity> getAccountUsers();
}
