package com.microservicio.fisibet.aplication.dto;

import java.time.LocalDateTime;

public class CreateAccountUserDto {
    private String eventType;

    private AccountUserDto accountUser;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public AccountUserDto getAccountUser() {
        return accountUser;
    }

    public void setAccountUser(AccountUserDto accountUser) {
        this.accountUser = accountUser;
    }
}
