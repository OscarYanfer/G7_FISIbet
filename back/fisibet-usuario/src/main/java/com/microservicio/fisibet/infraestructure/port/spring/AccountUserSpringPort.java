package com.microservicio.fisibet.infraestructure.port.spring;

import com.microservicio.fisibet.infraestructure.model.AccountUserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountUserSpringPort extends JpaRepository<AccountUserModel, Long> {
}
