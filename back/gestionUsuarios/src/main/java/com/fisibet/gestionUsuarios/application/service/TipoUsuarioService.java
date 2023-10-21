package com.fisibet.gestionUsuarios.application.service;

import com.fisibet.gestionUsuarios.application.common.UseCase;
import com.fisibet.gestionUsuarios.application.port.in.TipoUsuario.RegistrarTipoUsuarioCommand;
import com.fisibet.gestionUsuarios.application.port.in.TipoUsuario.RegistrarTipoUsuarioPort;
import com.fisibet.gestionUsuarios.application.port.out.TipoUsuario.LoadTipoUsuarioPort;
import com.fisibet.gestionUsuarios.application.port.out.TipoUsuario.SaveTipoUsuarioPort;
import com.fisibet.gestionUsuarios.application.port.out.TipoUsuario.UpdatTipoUsuarioPort;
import com.fisibet.gestionUsuarios.domain.TipoUsuario;
import jakarta.transaction.Transactional;

@UseCase
public class TipoUsuarioService implements RegistrarTipoUsuarioPort {

    private final LoadTipoUsuarioPort loadTipoUsuarioPort;

    private final SaveTipoUsuarioPort saveTipoUsuarioPort;

    private final UpdatTipoUsuarioPort updatTipoUsuarioPort;

    public TipoUsuarioService(LoadTipoUsuarioPort loadTipoUsuarioPort, SaveTipoUsuarioPort saveTipoUsuarioPort, UpdatTipoUsuarioPort updatTipoUsuarioPort) {
        this.loadTipoUsuarioPort = loadTipoUsuarioPort;
        this.saveTipoUsuarioPort = saveTipoUsuarioPort;
        this.updatTipoUsuarioPort = updatTipoUsuarioPort;
    }


    @Override
    public boolean actualizarTipoUsuario(RegistrarTipoUsuarioCommand registrarTipoUsuarioCommand) {
        return false;
    }

    @Transactional
    @Override
    public boolean registrarTipoUsuario(TipoUsuario tipoUsuario) {

        try{
            TipoUsuario tipoUsuario1 = loadTipoUsuarioPort.loadById(tipoUsuario.getIdTipoUsuario());

            if(tipoUsuario1 == null){
                saveTipoUsuarioPort.save(tipoUsuario);
            } else {
                throw new RuntimeException("Tipo usuario ya registrado");
            }

        } catch (Exception e){
            System.out.println("Error en service: " + e.getMessage());
        }

        return false;
    }
}
