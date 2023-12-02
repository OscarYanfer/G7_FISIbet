package com.microservicio.fisibet.infraestructure.model;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TicketModel {
    private Integer id;

    private String number;
    private Integer idAccountUser;
    private Integer status;
    private BigDecimal amountBet;
    private BigDecimal totalFee;
    private LocalDateTime registeredOn;
    private LocalDateTime updatedOn;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "TicketModel{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", idAccountUser=" + idAccountUser +
                ", status=" + status +
                ", amountBet=" + amountBet +
                ", totalFee=" + totalFee +
                ", registeredOn=" + registeredOn +
                ", updatedOn=" + updatedOn +
                '}';
    }
}
