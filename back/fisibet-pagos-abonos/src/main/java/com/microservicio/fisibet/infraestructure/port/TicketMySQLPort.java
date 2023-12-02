package com.microservicio.fisibet.infraestructure.port;

import com.microservicio.fisibet.aplication.port.TicketPort;
import com.microservicio.fisibet.domain.entity.TicketEntity;
import com.microservicio.fisibet.infraestructure.model.CardEvent;
import com.microservicio.fisibet.infraestructure.model.PayEvent;
import com.microservicio.fisibet.infraestructure.model.TicketEvent;
import com.microservicio.fisibet.infraestructure.model.WalletModel;
import com.microservicio.fisibet.infraestructure.port.spring.TicketSpringPort;
import com.microservicio.fisibet.infraestructure.port.spring.WalletSpringPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TicketMySQLPort implements TicketPort {
    @Autowired
    private TicketSpringPort ticketSpringPort;
    @Autowired
    private WalletSpringPort walletSpringPort;
    @Override
    public TicketEntity getTicketById(Integer ticketId) {
        return null;
    }

    @KafkaListener(topics = "ticket-topic", groupId = "msAbonoPago",containerFactory = "abonoPagoListener")
    public void processTicketEvents(TicketEvent ticketEvent){
            this.ticketSpringPort.save(ticketEvent.getTicket());
    }

    @KafkaListener(topics = "pay-topic", groupId = "msAbonoPago",containerFactory = "abonoPagoListener")
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
