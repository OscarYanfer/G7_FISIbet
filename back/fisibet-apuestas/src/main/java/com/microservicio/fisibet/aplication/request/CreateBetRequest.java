package com.microservicio.fisibet.aplication.request;

import java.math.BigDecimal;

public class CreateBetRequest {
    public Integer eventId;
    public String name;
    public String description;
    public BigDecimal pay;

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPay() {
        return pay;
    }

    public void setPay(BigDecimal pay) {
        this.pay = pay;
    }
}
