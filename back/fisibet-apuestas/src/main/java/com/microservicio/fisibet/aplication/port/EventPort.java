package com.microservicio.fisibet.aplication.port;

import com.microservicio.fisibet.domain.entity.EventEntity;

import java.util.List;

public interface EventPort {
    List<EventEntity> GetEventsEnabled();
    Integer registerEvent(EventEntity eventEntity);
}
