package com.ms.gestionapuestas.infraestructure.port.spring;

import com.ms.gestionapuestas.infraestructure.model.TicketBetModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketBetSpringPort extends JpaRepository<TicketBetModel, Long> {
}
