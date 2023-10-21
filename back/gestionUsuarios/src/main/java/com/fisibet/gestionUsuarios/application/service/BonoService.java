package com.fisibet.gestionUsuarios.application.service;

import com.fisibet.gestionUsuarios.application.common.UseCase;
import com.fisibet.gestionUsuarios.application.port.in.Bono.RegistrarBonoCommand;
import com.fisibet.gestionUsuarios.application.port.in.Bono.RegistrarBonoPort;
import com.fisibet.gestionUsuarios.application.port.out.Bono.LoadBonoPort;
import com.fisibet.gestionUsuarios.application.port.out.Bono.SaveBonoPort;
import com.fisibet.gestionUsuarios.application.port.out.Bono.UpdateBonoPort;
import com.fisibet.gestionUsuarios.domain.Bono;

@UseCase
public class BonoService implements RegistrarBonoPort {

    private final LoadBonoPort loadBonoPort;
    private final SaveBonoPort saveBonoPort;
    private final UpdateBonoPort updateBonoPort;

    public BonoService(LoadBonoPort loadBonoPort, SaveBonoPort saveBonoPort, UpdateBonoPort updateBonoPort) {
        this.loadBonoPort = loadBonoPort;
        this.saveBonoPort = saveBonoPort;
        this.updateBonoPort = updateBonoPort;
    }

    @Override
    public void actualizarBono(RegistrarBonoCommand command) {

    }

    @Override
    public void registrarBono(Bono bono) {
        try{
            Bono bono1 = loadBonoPort.loadById(bono.getIdBono());
            if(bono1 == null){
                saveBonoPort.save(bono);
            } else {
                throw new RuntimeException("Bono existente");
            }
        } catch (Exception e){
            System.out.println("Error BonoService: " + e.getMessage());
        }
    }
}
