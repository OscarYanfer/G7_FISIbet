package com.microservicio.fisibet.domain.entity;

import java.time.LocalDateTime;

public class TicketBetEntity {
    public Integer id;
    public Integer idBet;
    public Integer idTicket;
    public Integer status;
    public LocalDateTime registeredOn;
    public LocalDateTime updatedOn;
    public BetEntity bet;

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

    public BetEntity getBet() {
        return bet;
    }

    public void setBet(BetEntity bet) {
        this.bet = bet;
    }
}
