package com.microservicio.fisibet.aplication.port;

import com.microservicio.fisibet.domain.entity.BetEntity;

import java.util.List;

public interface BetPort {
    Integer registerBet(BetEntity betEntity);
    List<BetEntity> getBets();
}
