package com.microservicio.fisibet.infraestructure.port.spring;

import com.microservicio.fisibet.infraestructure.model.BetModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BetSpringPort extends JpaRepository<BetModel, Long> {
    @Query(value = "SELECT * FROM Apuesta WHERE status = 1", nativeQuery = true)
    List<BetModel> getBetsEnabled();
}
