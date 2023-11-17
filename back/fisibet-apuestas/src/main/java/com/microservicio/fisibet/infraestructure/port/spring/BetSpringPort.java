package com.microservicio.fisibet.infraestructure.port.spring;

import com.microservicio.fisibet.infraestructure.model.BetModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BetSpringPort extends JpaRepository<BetModel, Integer> {
    @Query(value = "SELECT * FROM Apuesta WHERE status = 1", nativeQuery = true)
    List<BetModel> getBetsEnabled();

    @Query(value = "SELECT * FROM Apuesta WHERE " +
            " ( event_id = :eventId ) && " +
            " ( status = 1 ) && " +
            " ( name = :name )", nativeQuery = true)
    BetModel getBetByEvent(@Param("eventId") Integer eventId,
                            @Param("name") String name);

    @Query(value = "SELECT * FROM Apuesta WHERE " +
            " ( event_id = :eventId ) ", nativeQuery = true)
    List<BetModel> getBetByEventId(@Param("eventId") Integer eventId);
}
