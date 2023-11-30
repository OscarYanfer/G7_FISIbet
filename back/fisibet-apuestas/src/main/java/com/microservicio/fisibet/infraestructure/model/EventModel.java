package com.microservicio.fisibet.infraestructure.model;



import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Evento")
public class EventModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "equipoA")
    private String equipoA;
    @Column(name = "equipoB")
    private String equipoB;
    @Column(name = "liga")
    private String liga;

    @Column(name = "fechaHora")
    private LocalDateTime fechaHora;
    @Column(name = "status")
    private Integer status;

    @Column(name = "registeredOn")
    private LocalDateTime registeredOn;

    @Column(name = "updatedOn")
    private LocalDateTime updatedOn;

    /*@OneToMany(targetEntity = BetModel.class, mappedBy = "id", orphanRemoval = false, fetch = FetchType.LAZY)
    private Set<BetModel> bets;*/

    /*@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "id", orphanRemoval = true, targetEntity = BetModel.class)
    private List<BetModel> bets = new ArrayList<>();*/

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "enterprise")
    private List<BetModel> bets;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEquipoA() {
        return equipoA;
    }

    public void setEquipoA(String equipoA) {
        this.equipoA = equipoA;
    }

    public String getEquipoB() {
        return equipoB;
    }

    public void setEquipoB(String equipoB) {
        this.equipoB = equipoB;
    }

    public String getLiga() {
        return liga;
    }

    public void setLiga(String liga) {
        this.liga = liga;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
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

    public List<BetModel> getBets() {
        return bets;
    }

    public void setBets(List<BetModel> bets) {
        this.bets = bets;
    }
}
