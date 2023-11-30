package com.microservicio.fisibet.aplication.request;

import java.math.BigDecimal;
import java.util.List;

public class UpdateEventRequest {
    public String equipoA;
    public String equipoB;
    public String liga;
    public String fechaHora;
    public BigDecimal payEquipoA;
    public BigDecimal payEquipoB;
    public BigDecimal payEmpate;
    public Integer betIdEquipoA;
    public Integer betIdEquipoB;
    public Integer betIdEmpate;

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

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public BigDecimal getPayEquipoA() {
        return payEquipoA;
    }

    public void setPayEquipoA(BigDecimal payEquipoA) {
        this.payEquipoA = payEquipoA;
    }

    public BigDecimal getPayEquipoB() {
        return payEquipoB;
    }

    public void setPayEquipoB(BigDecimal payEquipoB) {
        this.payEquipoB = payEquipoB;
    }

    public Integer getBetIdEquipoA() {
        return betIdEquipoA;
    }

    public void setBetIdEquipoA(Integer betIdEquipoA) {
        this.betIdEquipoA = betIdEquipoA;
    }

    public Integer getBetIdEquipoB() {
        return betIdEquipoB;
    }

    public void setBetIdEquipoB(Integer betIdEquipoB) {
        this.betIdEquipoB = betIdEquipoB;
    }

    public Integer getBetIdEmpate() {
        return betIdEmpate;
    }

    public void setBetIdEmpate(Integer betIdEmpate) {
        this.betIdEmpate = betIdEmpate;
    }

    public BigDecimal getPayEmpate() {
        return payEmpate;
    }

    public void setPayEmpate(BigDecimal payEmpate) {
        this.payEmpate = payEmpate;
    }
}
