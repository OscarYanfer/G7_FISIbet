package com.fisibet.gestionUsuarios.application.port.out.CuentaUsuario;

import com.fisibet.gestionUsuarios.domain.CuentaUsuario;

public interface LoadCuentaUsuarioPort {
    CuentaUsuario loadByCorreo(String correo);
}
