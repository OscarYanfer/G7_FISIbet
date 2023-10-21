package com.fisibet.gestionUsuarios.application.service;

import com.fisibet.gestionUsuarios.application.common.UseCase;
import com.fisibet.gestionUsuarios.application.port.in.Usuario.RegistrarUsuarioCommand;
import com.fisibet.gestionUsuarios.application.port.in.Usuario.RegistrarUsuarioPort;
import com.fisibet.gestionUsuarios.application.port.out.Usuario.LoadUsuarioPort;
import com.fisibet.gestionUsuarios.application.port.out.Usuario.SaveUsuarioPort;
import com.fisibet.gestionUsuarios.application.port.out.Usuario.UpdateUsuarioPort;
import com.fisibet.gestionUsuarios.domain.Usuario;

@UseCase
public class UsuarioService implements RegistrarUsuarioPort {

    private final LoadUsuarioPort loadUsuarioPort ;
    private final SaveUsuarioPort saveUsuarioPort;
    private final UpdateUsuarioPort updateUsuario;

    public UsuarioService(LoadUsuarioPort loadUsuarioPort, SaveUsuarioPort saveUsuarioPort, UpdateUsuarioPort updateUsuario) {
        this.loadUsuarioPort = loadUsuarioPort;
        this.saveUsuarioPort = saveUsuarioPort;
        this.updateUsuario = updateUsuario;
    }

    @Override
    public void actualizarUsuario(RegistrarUsuarioCommand command) {

    }

    @Override
    public void registrarUsuario(Usuario usuario) {
        try {
            Usuario usuario1 = loadUsuarioPort.load(usuario.getIdUsuario());
            if(usuario1 == null){
                saveUsuarioPort.save(usuario);
            } else {
                throw new RuntimeException("Usuario Registrado");
            }

        } catch (Exception e){
            System.out.printf("Error UsuarioService: " + e.getMessage());
        }
    }
}
