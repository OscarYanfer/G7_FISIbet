package com.microservicio.fisibet.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TicketEntity {
    public Integer id;
    public String number;
    public Integer idAccountUser;
    public BigDecimal amountBet;
    public BigDecimal totalFee;
    public Integer status;
    public LocalDateTime registeredOn;
    public LocalDateTime updatedOn;

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
}
