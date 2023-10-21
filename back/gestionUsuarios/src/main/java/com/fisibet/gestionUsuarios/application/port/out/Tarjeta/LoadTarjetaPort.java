package com.fisibet.gestionUsuarios.application.port.out.Tarjeta;

import com.fisibet.gestionUsuarios.domain.Tarjeta;

public interface LoadTarjetaPort {
    Tarjeta load(int id);
}
