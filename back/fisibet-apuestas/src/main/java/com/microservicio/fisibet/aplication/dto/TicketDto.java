package com.microservicio.fisibet.aplication.dto;

import com.microservicio.fisibet.domain.entity.TicketBetEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class TicketDto {
    public Integer id;
    public String number;
    public Integer idAccountUser;
    public BigDecimal amountBet;
    public BigDecimal totalFee;
    public Integer status;
    public LocalDateTime registeredOn;
    public LocalDateTime updatedOn;
    public List<TicketBetEntity> ticketBets;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getIdAccountUser() {
        return idAccountUser;
    }

    public void setIdAccountUser(Integer idAccountUser) {
        this.idAccountUser = idAccountUser;
    }

    public BigDecimal getAmountBet() {
        return amountBet;
    }

    public void setAmountBet(BigDecimal amountBet) {
        this.amountBet = amountBet;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
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

    public List<TicketBetEntity> getTicketBets() {
        return ticketBets;
    }

    public void setTicketBets(List<TicketBetEntity> ticketBets) {
        this.ticketBets = ticketBets;
    }
}
