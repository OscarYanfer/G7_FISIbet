package com.microservicio.fisibet.aplication.request;

import java.math.BigDecimal;
import java.util.ArrayList;

public class CreateTicketRequest {
    public Integer idAccountUser;
    public BigDecimal amountBet;
    public BigDecimal totalFee;
    public ArrayList<Integer> betIds;

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

    public ArrayList<Integer> getBetIds() {
        return betIds;
    }

    public void setBetIds(ArrayList<Integer> betIds) {
        this.betIds = betIds;
    }
}
