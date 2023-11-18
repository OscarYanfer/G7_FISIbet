package com.microservicio.fisibet.infraestructure.port.spring;

import com.microservicio.fisibet.infraestructure.model.TicketModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TicketSpringPort extends JpaRepository<TicketModel, Integer> {
    @Query(value = "SELECT COUNT(*) FROM ticket", nativeQuery = true)
    Integer getCountRows();
}
