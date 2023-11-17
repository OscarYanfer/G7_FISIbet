package com.microservicio.fisibet.infraestructure.model;


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
}
