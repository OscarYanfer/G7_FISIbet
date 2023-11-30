package com.microservicio.fisibet.infraestructure.port.spring;

import com.microservicio.fisibet.infraestructure.model.AccountUserModel;
import com.microservicio.fisibet.infraestructure.model.SessionUserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountUserSpringPort extends JpaRepository<AccountUserModel, Integer> {

    @Query(value = "SELECT DISTINCT * FROM cuenta_usuario WHERE username = :username ", nativeQuery = true)
    AccountUserModel getAccountUserByName(@Param("username") String username);
}
