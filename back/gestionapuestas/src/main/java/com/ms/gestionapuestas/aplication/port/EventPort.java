package com.ms.gestionapuestas.aplication.port;

import com.ms.gestionapuestas.domain.entity.EventEntity;

import java.util.List;

public interface EventPort {
    List<EventEntity> GetEventsEnabled();
    Integer registerEvent(EventEntity eventEntity);
}
