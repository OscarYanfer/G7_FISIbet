package com.microservicio.fisibet.infraestructure.port;

import com.microservicio.fisibet.aplication.port.WalletPort;
import com.microservicio.fisibet.domain.entity.CardEntity;
import com.microservicio.fisibet.infraestructure.model.CardEvent;
import com.microservicio.fisibet.infraestructure.port.spring.CardSpringPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CardMySQLPort implements WalletPort {
    @Autowired
    private CardSpringPort cardSpringPort;
    @Override
    public CardEntity getWalletByAccountId(Integer accountId) {
        return null;
    }

    @KafkaListener(topics = "card-topic", groupId = "msAbonoPago",containerFactory = "abonoPagoListener")
    public void processProductEvents(CardEvent cardEvent){
        this.cardSpringPort.save(cardEvent.getWalletModel());
    }
}
