package com.fisibet.gestionUsuarios.application.port.out.TipoUsuario;

import com.fisibet.gestionUsuarios.domain.CuentaUsuario;
import com.fisibet.gestionUsuarios.domain.TipoUsuario;

public interface SaveTipoUsuarioPort {

    void save(TipoUsuario tipoUsuario);
}
