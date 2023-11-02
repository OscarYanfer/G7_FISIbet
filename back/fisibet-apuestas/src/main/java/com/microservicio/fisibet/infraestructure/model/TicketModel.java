package com.microservicio.fisibet.infraestructure.model;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ticket")
public class TicketModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "number")
    private String number;
    @Column(name = "idAccountUser")
    private Integer idAccountUser;
    @Column(name = "status")
    private Integer status;
    @Column(name = "amountBet")
    private BigDecimal amountBet;
    @Column(name = "totalFee")
    private BigDecimal totalFee;
    @Column(name = "registered")
    private LocalDateTime registered;
    @Column(name = "updated")
    private LocalDateTime updated;

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

    public LocalDateTime getRegistered() {
        return registered;
    }

    public void setRegistered(LocalDateTime registered) {
        this.registered = registered;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }
}
