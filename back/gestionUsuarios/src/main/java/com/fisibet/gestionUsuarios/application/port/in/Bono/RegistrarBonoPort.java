package com.fisibet.gestionUsuarios.application.port.in.Bono;

import com.fisibet.gestionUsuarios.domain.Bono;

public interface RegistrarBonoPort {

    public void actualizarBono(RegistrarBonoCommand command);

    public void registrarBono(Bono bono);

}
