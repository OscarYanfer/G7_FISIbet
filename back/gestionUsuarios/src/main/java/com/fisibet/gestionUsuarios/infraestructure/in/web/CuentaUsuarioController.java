package com.fisibet.gestionUsuarios.infraestructure.in.web;

import com.fisibet.gestionUsuarios.application.port.in.CuentaUsuario.RegistrarCuentaUsuarioPort;
import com.fisibet.gestionUsuarios.domain.CuentaUsuario;
import com.fisibet.gestionUsuarios.infraestructure.common.WebAdapter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
public class CuentaUsuarioController {

    private final RegistrarCuentaUsuarioPort cuentaUsuarioPort;

    public CuentaUsuarioController(RegistrarCuentaUsuarioPort cuentaUsuarioPort) {
        this.cuentaUsuarioPort = cuentaUsuarioPort;
    }

    @PostMapping(path = "/cuentaUsuario/registrar")
    public void registrarUsuario(@RequestBody CuentaUsuario cuentaUsuario){
        cuentaUsuarioPort.registrarCuentaUsuario(cuentaUsuario);
    }
}
