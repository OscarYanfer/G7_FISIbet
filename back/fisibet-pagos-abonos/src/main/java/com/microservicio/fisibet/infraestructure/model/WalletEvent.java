package com.microservicio.fisibet.infraestructure.model;

public class WalletEvent {
    private String eventType;
    private WalletModel walletModel;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public WalletModel getWalletModel() {
        return walletModel;
    }

    public void setWalletModel(WalletModel walletModel) {
        this.walletModel = walletModel;
    }

    @Override
    public String toString() {
        return "WalletEvent{" +
                "eventType='" + eventType + '\'' +
                ", walletModel=" + walletModel +
                '}';
    }
}
