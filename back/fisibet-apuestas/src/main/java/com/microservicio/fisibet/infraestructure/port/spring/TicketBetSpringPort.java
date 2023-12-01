package com.microservicio.fisibet.infraestructure.port.spring;

import com.microservicio.fisibet.infraestructure.model.TicketBetModel;
import com.microservicio.fisibet.infraestructure.model.TicketModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TicketBetSpringPort extends JpaRepository<TicketBetModel, Long> {

    @Query(value = "SELECT DISTINCT ticketapuesta.* FROM evento " +
            " INNER JOIN apuesta ON evento.id = apuesta.event_id " +
            " INNER JOIN ticketapuesta ON ticketapuesta.id_bet = apuesta.id" +
            " WHERE evento.id = :id AND ticketapuesta.status = 1;", nativeQuery = true)
    List<TicketBetModel> getTicketBetsByBetId(@Param("id") Integer id);

}
