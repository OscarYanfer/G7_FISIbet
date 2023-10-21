package com.fisibet.gestionUsuarios.infraestructure.out.persistence.TarjetaPersistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringTarjetaRepository extends JpaRepository<TarjetaEntity, Integer> {

    TarjetaEntity findByIdTarjeta(int i);
}
