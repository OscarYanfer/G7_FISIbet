package com.fisibet.gestionUsuarios.application.port.in.Usuario;

import com.fisibet.gestionUsuarios.domain.Usuario;

public interface RegistrarUsuarioPort {

    public void actualizarUsuario(RegistrarUsuarioCommand command);

    public void registrarUsuario(Usuario usuario);
}
