package com.fisibet.gestionUsuarios.application.port.in.Bono;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrarBonoCommand {
    int idBono;

    String nombreBono;

    int estado;

    String registered;

    String updated;

}
