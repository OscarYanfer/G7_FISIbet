package com.fisibet.gestionUsuarios.infraestructure.out.persistence.CuentaUsuarioPersistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringCuentaUsuarioRepository extends JpaRepository<CuentaUsuarioEntity, Integer> {
    CuentaUsuarioEntity findByCorreo(String correo);


}
