package com.fisibet.gestionUsuarios.application.port.out.Usuario;

import com.fisibet.gestionUsuarios.domain.Usuario;

public interface SaveUsuarioPort {
    void save(Usuario usuario);
}
