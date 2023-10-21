package com.fisibet.gestionUsuarios.application.port.out.TipoUsuario;

import com.fisibet.gestionUsuarios.domain.TipoUsuario;

public interface LoadTipoUsuarioPort {
    TipoUsuario loadById(int id);
}
