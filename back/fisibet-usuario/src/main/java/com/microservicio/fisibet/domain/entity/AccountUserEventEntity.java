package com.microservicio.fisibet.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class AccountUserEventEntity {
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
