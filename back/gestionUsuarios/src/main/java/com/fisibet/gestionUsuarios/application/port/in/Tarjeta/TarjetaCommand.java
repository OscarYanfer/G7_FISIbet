package com.fisibet.gestionUsuarios.application.port.in.Tarjeta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TarjetaCommand {
    int idTarjeta;

    String numero;

    String csv;

    String fechaVencimiento;

    String registered;

    String updated;
}
