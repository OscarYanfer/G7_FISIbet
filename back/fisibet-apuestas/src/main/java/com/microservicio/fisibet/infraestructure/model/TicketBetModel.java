package com.microservicio.fisibet.infraestructure.model;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ticketapuesta")
public class TicketBetModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "idBet")
    private Integer idBet;

    @Column(name = "idTicket")
    private Integer idTicket;

    @Column(name = "status")
    private Integer status;

    @Column(name = "registeredOn")
    private LocalDateTime registeredOn;

    @Column(name = "updatedOn")
    private LocalDateTime updatedOn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTicket", insertable = false, updatable = false)
    @Fetch(FetchMode.JOIN)
    private TicketModel ticket;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idBet", insertable = false, updatable = false)
    @Fetch(FetchMode.JOIN)
    private BetModel bet;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdBet() {
        return idBet;
    }

    public void setIdBet(Integer idBet) {
        this.idBet = idBet;
    }

    public Integer getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(LocalDateTime registeredOn) {
        this.registeredOn = registeredOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    public TicketModel getTicket() {
        return ticket;
    }

    public void setTicket(TicketModel ticket) {
        this.ticket = ticket;
    }

    public BetModel getBet() {
        return bet;
    }

    public void setBet(BetModel bet) {
        this.bet = bet;
    }
}
