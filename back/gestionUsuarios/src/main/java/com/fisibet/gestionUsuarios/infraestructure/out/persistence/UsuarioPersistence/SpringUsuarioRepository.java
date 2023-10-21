package com.fisibet.gestionUsuarios.infraestructure.out.persistence.UsuarioPersistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringUsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    UsuarioEntity findById(int id);
}
