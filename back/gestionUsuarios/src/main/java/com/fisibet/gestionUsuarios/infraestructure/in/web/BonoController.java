package com.fisibet.gestionUsuarios.infraestructure.in.web;

import com.fisibet.gestionUsuarios.application.port.in.Bono.RegistrarBonoPort;
import com.fisibet.gestionUsuarios.domain.Bono;
import com.fisibet.gestionUsuarios.infraestructure.common.WebAdapter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
public class BonoController {

    private final RegistrarBonoPort registrarBonoPort;

    public BonoController(RegistrarBonoPort registrarBonoPort) {
        this.registrarBonoPort = registrarBonoPort;
    }

    @PostMapping(path = "/bono/registrar")
    public void registrarBono(@RequestBody Bono bono){
        registrarBonoPort.registrarBono(bono);
    }

}
