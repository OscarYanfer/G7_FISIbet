package com.microservicio.fisibet.infraestructure.port.spring;

import com.microservicio.fisibet.infraestructure.model.EventModel;
import com.microservicio.fisibet.infraestructure.model.TicketModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TicketSpringPort extends JpaRepository<TicketModel, Integer> {
    @Query(value = "SELECT COUNT(*) FROM ticket", nativeQuery = true)
    Integer getCountRows();

    @Query(value = "SELECT DISTINCT ticket.* FROM ticket " +
            " INNER JOIN ticketapuesta ON ticket.id = ticketapuesta.id_ticket " +
            // " INNER JOIN apuesta ON ticketapuesta.id_bet = apuesta.id" +
            " WHERE ticket.status = 1 ", nativeQuery = true)
    List<TicketModel> getTickets();


    @Query(value = "SELECT DISTINCT ticket.* FROM ticket " +
            " INNER JOIN ticketapuesta ON ticket.id = ticketapuesta.id_ticket " +
            //" INNER JOIN apuesta ON ticketapuesta.id_bet = apuesta.id" +
            " WHERE ticket.status = 1 AND ticket.id = :id ", nativeQuery = true)
    TicketModel getTicketById(@Param("id") Integer id);
}
