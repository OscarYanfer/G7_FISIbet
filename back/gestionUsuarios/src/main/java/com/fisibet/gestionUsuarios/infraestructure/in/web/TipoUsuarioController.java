package com.fisibet.gestionUsuarios.infraestructure.in.web;

import com.fisibet.gestionUsuarios.application.port.in.TipoUsuario.RegistrarTipoUsuarioPort;
import com.fisibet.gestionUsuarios.domain.TipoUsuario;
import com.fisibet.gestionUsuarios.infraestructure.common.WebAdapter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
public class TipoUsuarioController {

    private final RegistrarTipoUsuarioPort registrarTipoUsuarioPort;

    public TipoUsuarioController(RegistrarTipoUsuarioPort registrarTipoUsuarioPort) {
        this.registrarTipoUsuarioPort = registrarTipoUsuarioPort;
    }

    @PostMapping (path = "/tipoUsuario/registrar")
    public void registrarTipoUsuario(@RequestBody TipoUsuario tipoUsuario){
        registrarTipoUsuarioPort.registrarTipoUsuario(tipoUsuario);
    }
}
