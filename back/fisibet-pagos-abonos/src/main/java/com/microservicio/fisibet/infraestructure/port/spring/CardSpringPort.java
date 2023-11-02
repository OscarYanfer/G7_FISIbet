package com.microservicio.fisibet.infraestructure.port.spring;

import com.microservicio.fisibet.infraestructure.model.CardModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardSpringPort extends JpaRepository<CardModel, Long> {
}
