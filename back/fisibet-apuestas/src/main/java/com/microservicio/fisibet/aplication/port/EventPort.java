package com.microservicio.fisibet.aplication.port;

import com.microservicio.fisibet.domain.entity.EventEntity;

import java.util.List;

public interface EventPort {
    List<EventEntity> GetEventsEnabled();
    EventEntity registerEvent(EventEntity eventEntity);
    EventEntity updateEventById(EventEntity eventEntity, Integer eventId);
    EventEntity getEventById(Integer id);
}
