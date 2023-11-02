package com.microservicio.fisibet.infraestructure.model;

public class AccountUserEvent {
    private String eventType;
    private AccountUserModel accountUser;

    public AccountUserEvent(String eventType, AccountUserModel accountUser) {
        this.eventType = eventType;
        this.accountUser = accountUser;
    }

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

    @Override
    public String toString() {
        return "AccountUserEvent{" +
                "eventType='" + eventType + '\'' +
                ", accountUser=" + accountUser +
                '}';
    }
}
