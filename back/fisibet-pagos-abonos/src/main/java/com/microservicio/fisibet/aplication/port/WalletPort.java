package com.microservicio.fisibet.aplication.port;

import com.microservicio.fisibet.domain.entity.CardEntity;

public interface WalletPort {
    CardEntity getWalletByAccountId(Integer accountId);
}
