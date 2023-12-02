package com.microservicio.fisibet.infraestructure.port.spring;

import com.microservicio.fisibet.infraestructure.model.WalletModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletSpringPort extends JpaRepository<WalletModel, Integer> {

}
