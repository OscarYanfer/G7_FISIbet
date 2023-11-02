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
