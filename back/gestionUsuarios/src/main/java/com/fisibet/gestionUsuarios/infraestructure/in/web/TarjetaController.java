package com.fisibet.gestionUsuarios.infraestructure.in.web;

import com.fisibet.gestionUsuarios.application.port.in.Tarjeta.TarjetaPort;
import com.fisibet.gestionUsuarios.domain.Tarjeta;
import com.fisibet.gestionUsuarios.infraestructure.common.WebAdapter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
public class TarjetaController {
    private final TarjetaPort port;


    public TarjetaController(TarjetaPort port) {
        this.port = port;
    }

    @PostMapping(path = "/tarjeta/registrar")
    public void registrarTarjeta(@RequestBody Tarjeta tarjeta){
        port.registrarTarjeta(tarjeta);
    }
}
