package com.microservicio.fisibet.infraestructure.port;

import com.microservicio.fisibet.aplication.port.WalletPort;
import com.microservicio.fisibet.domain.entity.CardEntity;
import com.microservicio.fisibet.infraestructure.model.CardEvent;
import com.microservicio.fisibet.infraestructure.model.WalletEvent;
import com.microservicio.fisibet.infraestructure.port.spring.WalletSpringPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class WalletMySQLPort implements WalletPort {
    @Autowired
    private WalletSpringPort walletSpringPort;
    @Override
    public CardEntity getWalletByAccountId(Integer accountId) {
        return null;
    }

    @KafkaListener(topics = "wallet-topic", groupId = "msAbonoPago",containerFactory = "abonoPagoListener")
    public void processWalletEvents(WalletEvent walletEvent){
        this.walletSpringPort.save(walletEvent.getWalletModel());
    }
}
