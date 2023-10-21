package com.fisibet.gestionUsuarios.infraestructure.out.persistence.BonoPersistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringBonoRepository extends JpaRepository<BonoEntity, Integer> {
    BonoEntity findByIdBono(int id);
}
