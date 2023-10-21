package com.fisibet.gestionUsuarios.application.port.in.Tarjeta;

import com.fisibet.gestionUsuarios.domain.Tarjeta;

public interface TarjetaPort {
    public void actualizarTarjeta(TarjetaCommand command);

    public void registrarTarjeta(Tarjeta tarjeta);
}
