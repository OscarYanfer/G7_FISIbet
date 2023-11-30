package com.microservicio.fisibet.aplication.port;

import com.microservicio.fisibet.aplication.dto.AuthDto;
import com.microservicio.fisibet.domain.entity.SessionUserEntity;

public interface SessionUserPort {
    SessionUserEntity login(SessionUserEntity sessionUserEntity);

    Integer disconnectUsers();
}
