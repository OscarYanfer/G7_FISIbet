package com.ms.gestionapuestas.aplication.port;

import com.ms.gestionapuestas.domain.entity.BetEntity;

import java.util.List;

public interface BetPort {
    Integer registerBet(BetEntity betEntity);
    List<BetEntity> getBets();
}
