package com.microservicio.fisibet.infraestructure.port.spring;

import com.microservicio.fisibet.infraestructure.model.EventModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventSpringPort extends JpaRepository<EventModel, Long> {
    @Query(value = "SELECT * FROM Evento WHERE status = 1", nativeQuery = true)
    List<EventModel> getEventsEnabled();

}
