package com.fisibet.gestionUsuarios.application.service;

import com.fisibet.gestionUsuarios.application.common.UseCase;
import com.fisibet.gestionUsuarios.application.port.in.Tarjeta.TarjetaCommand;
import com.fisibet.gestionUsuarios.application.port.in.Tarjeta.TarjetaPort;
import com.fisibet.gestionUsuarios.application.port.out.Tarjeta.LoadTarjetaPort;
import com.fisibet.gestionUsuarios.application.port.out.Tarjeta.SaveTarjetaPort;
import com.fisibet.gestionUsuarios.application.port.out.Tarjeta.UpdateTarjetaPort;
import com.fisibet.gestionUsuarios.domain.Tarjeta;

@UseCase
public class TarjetaService implements TarjetaPort {

    private final LoadTarjetaPort loadTarjetaPort;

    private final SaveTarjetaPort saveTarjetaPort;

    private final UpdateTarjetaPort updateTarjetaPort;

    public TarjetaService(LoadTarjetaPort loadTarjetaPort, SaveTarjetaPort saveTarjetaPort, UpdateTarjetaPort updateTarjetaPort) {
        this.loadTarjetaPort = loadTarjetaPort;
        this.saveTarjetaPort = saveTarjetaPort;
        this.updateTarjetaPort = updateTarjetaPort;
    }


    @Override
    public void actualizarTarjeta(TarjetaCommand command) {

    }

    @Override
    public void registrarTarjeta(Tarjeta tarjeta) {
        try{
            Tarjeta tarjeta1 = loadTarjetaPort.load(tarjeta.getIdTarjeta());
            if(tarjeta1 == null){
                saveTarjetaPort.save(tarjeta);
            } else {
                throw new RuntimeException("Tarjeta Existente");
            }
        } catch (Exception e){
            System.out.println("Error TarjetaService: " + e.getMessage());
        }
    }
}
