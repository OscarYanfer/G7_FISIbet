package com.fisibet.gestionUsuarios.application.port.in.TipoUsuario;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrarTipoUsuarioCommand {

    int idTipoUsuario;

    String nombreTipoUsuario;

    String registered;

    String updated;

}
