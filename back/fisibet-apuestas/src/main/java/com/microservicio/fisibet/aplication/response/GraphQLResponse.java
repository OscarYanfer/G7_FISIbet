package com.microservicio.fisibet.aplication.response;

public class GraphQLResponse<T>{
    private String eventType;
    private T content = null;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
