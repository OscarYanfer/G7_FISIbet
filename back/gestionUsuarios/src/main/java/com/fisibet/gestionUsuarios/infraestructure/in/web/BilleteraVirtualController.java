package com.fisibet.gestionUsuarios.infraestructure.in.web;


import com.fisibet.gestionUsuarios.application.port.in.BilleteraVirtual.RegistrarBilleteraVirtualPort;
import com.fisibet.gestionUsuarios.domain.BilleteraVirtual;
import com.fisibet.gestionUsuarios.infraestructure.common.WebAdapter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
public class BilleteraVirtualController {
    private final RegistrarBilleteraVirtualPort registrarBilleteraVirtualPort;


    public BilleteraVirtualController(RegistrarBilleteraVirtualPort registrarBilleteraVirtualPort) {
        this.registrarBilleteraVirtualPort = registrarBilleteraVirtualPort;
    }

    @PostMapping(path = "/billeteraVirtual/registrar")
    public void registrarBilltera(@RequestBody BilleteraVirtual billeteraVirtual){
        registrarBilleteraVirtualPort.registrarBilleteraVirtual(billeteraVirtual);
    }
}
