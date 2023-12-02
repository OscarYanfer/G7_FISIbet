package com.microservicio.fisibet.infraestructure.port;

import com.microservicio.fisibet.aplication.port.WalletPort;
import com.microservicio.fisibet.infraestructure.model.CardEvent;
import com.microservicio.fisibet.infraestructure.model.TicketEvent;
import com.microservicio.fisibet.infraestructure.model.TicketModel;
import com.microservicio.fisibet.infraestructure.port.spring.WalletSpringPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class WalletMySQLPort implements WalletPort {
    @Autowired
    private WalletSpringPort walletSpringPort;
    /*@KafkaListener(topics = "pay-topic", groupId = "msUsuarios", containerFactory = "userListener")
    public void processProductEvents(TicketModel ticketModel){
        System.out.println("GAAAA: "+ticketModel.toString());
        //this.walletSpringPort.save(cardEvent.getWalletModel());
    }*/
}
