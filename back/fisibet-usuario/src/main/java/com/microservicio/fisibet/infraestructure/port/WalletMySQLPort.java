package com.microservicio.fisibet.infraestructure.port;

import com.microservicio.fisibet.aplication.port.WalletPort;
import com.microservicio.fisibet.infraestructure.model.*;
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
    @KafkaListener(topics = "pay-topic", groupId = "msUsuarios",containerFactory = "userListener")
    public void processPay(PayEvent payEvent){
        System.out.println("Que pocede: "+payEvent);
        WalletModel walletModel = this.walletSpringPort.findById(payEvent.getAccountId()).orElse(null);
        if(walletModel.getSaldo() == null){
            walletModel.setSaldo(payEvent.getTotalFee());
        }else{
            walletModel.setSaldo(walletModel.getSaldo().add(payEvent.getTotalFee()));
        }
        WalletModel walletModel1 = this.walletSpringPort.save(walletModel);
    }
}
