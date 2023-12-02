package com.microservicio.fisibet.infraestructure.port.spring;

import com.microservicio.fisibet.infraestructure.model.SessionUserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface SessionUserSpringPort extends JpaRepository<SessionUserModel, Long> {
    @Query(value = "SELECT DISTINCT * FROM sessionuser WHERE username = :username ", nativeQuery = true)
    SessionUserModel getSessionUserByName(@Param("username") String username);
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE sessionuser " +
            "SET conectado = 1 " +
            "WHERE username = :username ", nativeQuery = true)
    Integer connectSessionUser(@Param("username") String username);
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "UPDATE sessionuser " +
            "SET conectado = 0 WHERE conectado = 1 ", nativeQuery = true)
    Integer disconnectSessionUsers();
}
