package com.microservicio.fisibet.infraestructure.model;

public class AccountUserEvent {
    private String eventType;
    private AccountUserModel accountUser;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public AccountUserModel getAccountUser() {
        return accountUser;
    }

    public void setAccountUser(AccountUserModel accountUser) {
        this.accountUser = accountUser;
    }
}
