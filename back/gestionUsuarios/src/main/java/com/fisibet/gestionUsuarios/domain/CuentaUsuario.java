package com.fisibet.gestionUsuarios.domain;

import lombok.Data;

import java.util.List;

@Data
public class CuentaUsuario
{
    int idCuentaUsuario;

    String correo;

    String contrasenia;

    String registered;

    String updated;

    //relationship with another tables

    // USUARIO
    Usuario usuario;

    //int idUsuario;

    //int idTipoUsuario;

    //BILLETERA
    BilleteraVirtual billetera;
    //int idBilleteraVirtual;

    //TARJETA
    List<Tarjeta> tarjetas;
    List<Integer> listaIdsTarjetas;


    public void registrarUsario(String corr, String contra){

        this.correo = corr;
        this.contrasenia = contra;

    }

    public void registrarTarjeta(Tarjeta nuevaTarjeta){

    }

}
