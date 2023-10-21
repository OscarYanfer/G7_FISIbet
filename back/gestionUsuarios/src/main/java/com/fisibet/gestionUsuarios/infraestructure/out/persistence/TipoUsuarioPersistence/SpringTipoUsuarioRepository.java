package com.fisibet.gestionUsuarios.infraestructure.out.persistence.TipoUsuarioPersistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringTipoUsuarioRepository extends JpaRepository<TipoUsuarioEntity, Integer> {

    TipoUsuarioEntity findById(int id);
}
