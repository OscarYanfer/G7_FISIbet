package com.microservicio.fisibet.infraestructure.model;

public class CardEvent {
    private String eventType;
    private CardModel cardModel;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public CardModel getWalletModel() {
        return cardModel;
    }

    public void setWalletModel(CardModel cardModel) {
        this.cardModel = cardModel;
    }

    @Override
    public String toString() {
        return "WalletEvent{" +
                "eventType='" + eventType + '\'' +
                ", walletModel=" + cardModel +
                '}';
    }
}
