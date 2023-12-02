package com.microservicio.fisibet.infraestructure.port.spring;

import com.microservicio.fisibet.infraestructure.model.EventModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventSpringPort extends JpaRepository<EventModel, Integer> {
    @Query(value = "SELECT DISTINCT evento.* FROM evento " +
            "INNER JOIN apuesta ON evento.id = apuesta.event_id ", nativeQuery = true)
    List<EventModel> getEventsEnabled();


}
