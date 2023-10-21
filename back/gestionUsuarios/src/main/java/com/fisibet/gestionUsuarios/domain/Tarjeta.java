package com.fisibet.gestionUsuarios.domain;

import lombok.Data;

@Data
public class Tarjeta {
    int idTarjeta;

    String numero;

    String csv;

    String fechaVencimiento;

    String registered;

    String updated;
}
