package com.microservicio.fisibet.infraestructure.port.spring;

import com.microservicio.fisibet.infraestructure.model.TicketModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketSpringPort extends JpaRepository<TicketModel, Long> {
}
