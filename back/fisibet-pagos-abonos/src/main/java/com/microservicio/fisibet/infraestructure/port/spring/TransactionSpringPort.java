package com.microservicio.fisibet.infraestructure.port.spring;

import com.microservicio.fisibet.infraestructure.model.TransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionSpringPort extends JpaRepository<TransactionModel, Long> {
}
