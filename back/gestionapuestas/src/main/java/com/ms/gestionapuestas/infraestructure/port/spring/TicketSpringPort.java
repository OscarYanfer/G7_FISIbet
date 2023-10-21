package com.ms.gestionapuestas.infraestructure.port.spring;

import com.ms.gestionapuestas.infraestructure.model.EventModel;
import com.ms.gestionapuestas.infraestructure.model.TicketModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TicketSpringPort extends JpaRepository<TicketModel, Long> {
    @Query(value = "SELECT COUNT(*) FROM ticket", nativeQuery = true)
    Integer getCountRows();
}
