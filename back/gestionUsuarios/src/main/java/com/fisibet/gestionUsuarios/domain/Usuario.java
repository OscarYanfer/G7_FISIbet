package com.fisibet.gestionUsuarios.domain;

import lombok.Data;

@Data
public class Usuario {
    private int idUsuario;

    private String nombreUsuario;

    private String dni;

    private String registered;

    private String updated;

}
