package com.fisibet.gestionUsuarios.application.port.in.TipoUsuario;

import com.fisibet.gestionUsuarios.domain.TipoUsuario;

public interface RegistrarTipoUsuarioPort {

    public boolean actualizarTipoUsuario(RegistrarTipoUsuarioCommand registrarTipoUsuarioCommand);

    public boolean registrarTipoUsuario(TipoUsuario tipoUsuario);

}
