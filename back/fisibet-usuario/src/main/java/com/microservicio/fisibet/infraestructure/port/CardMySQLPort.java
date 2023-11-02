package com.microservicio.fisibet.infraestructure.port;

import com.microservicio.fisibet.aplication.port.CardPort;
import com.microservicio.fisibet.domain.entity.CardEntity;
import com.microservicio.fisibet.infraestructure.mapper.CardInfraMapper;
import com.microservicio.fisibet.infraestructure.model.CardEvent;
import com.microservicio.fisibet.infraestructure.model.CardModel;
import com.microservicio.fisibet.infraestructure.port.spring.CardSpringPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class CardMySQLPort implements CardPort {
    @Autowired
    private CardInfraMapper cardInfraMapper;
    @Autowired
    private CardSpringPort cardSpringPort;
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    @Override
    public CardEntity registerWallet(CardEntity cardEntity) {
        cardEntity.setStatus(1);
        cardEntity.setRegisteredOn(LocalDateTime.now());
        CardModel cardModel = this.cardInfraMapper.convertCardEntityToCardModel(cardEntity);
        CardModel cardModel1 = this.cardSpringPort.save(cardModel);
        CardEvent cardEvent = new CardEvent();
        cardEvent.setEventType("CreateCard");
        cardEvent.setWalletModel(cardModel1);
        kafkaTemplate.send("card-topic", cardEvent);
        return this.cardInfraMapper.convertoCardModelToCardEntity(cardModel1);
    }
}
