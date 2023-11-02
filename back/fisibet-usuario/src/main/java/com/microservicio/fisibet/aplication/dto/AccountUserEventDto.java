package com.microservicio.fisibet.aplication.dto;

import com.microservicio.fisibet.domain.entity.AccountUserEntity;

public class AccountUserEventDto {
    private String eventType;
    private AccountUserEntity accountUser;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public AccountUserEntity getAccountUser() {
        return accountUser;
    }

    public void setAccountUser(AccountUserEntity accountUser) {
        this.accountUser = accountUser;
    }
}
