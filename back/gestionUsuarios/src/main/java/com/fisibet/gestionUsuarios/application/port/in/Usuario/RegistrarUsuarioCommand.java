package com.fisibet.gestionUsuarios.application.port.in.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrarUsuarioCommand {

    private int idUsuario;

    private String nombreUsuario;

    private String dni;

    private String registered;

    private String updated;

}
