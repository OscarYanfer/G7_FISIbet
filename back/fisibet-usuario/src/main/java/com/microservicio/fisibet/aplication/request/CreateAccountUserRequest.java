package com.microservicio.fisibet.aplication.request;

public class CreateAccountUserRequest {
    private String eventType;
    private AccountUserRequest accountUser;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public AccountUserRequest getAccountUser() {
        return accountUser;
    }

    public void setAccountUser(AccountUserRequest accountUser) {
        this.accountUser = accountUser;
    }
}
