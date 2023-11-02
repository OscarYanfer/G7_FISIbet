package com.microservicio.fisibet.infraestructure.model;

public class TicketEvent {
    private String eventType;
    private TicketModel ticket;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public TicketModel getTicket() {
        return ticket;
    }

    public void setTicket(TicketModel ticket) {
        this.ticket = ticket;
    }
}
