package com.fisibet.gestionUsuarios.infraestructure.out.persistence.CuentaUsuarioPersistence;

import com.fisibet.gestionUsuarios.domain.CuentaUsuario;

public class CuentaUsuarioMapper {

    public static CuentaUsuario entityToDomain (CuentaUsuarioEntity cuentaUsuarioEntity){
        CuentaUsuario cuentaUsuario = new CuentaUsuario();

        cuentaUsuario.setIdCuentaUsuario(cuentaUsuarioEntity.getId());
        cuentaUsuario.setCorreo(cuentaUsuarioEntity.getCorreo());
        cuentaUsuario.setContrasenia(cuentaUsuarioEntity.getContrasenia());
        cuentaUsuario.setRegistered(cuentaUsuarioEntity.getRegistered());
        cuentaUsuario.setUpdated(cuentaUsuarioEntity.getUpdated());

        return cuentaUsuario;
    }

    public static CuentaUsuarioEntity domainToEntity(CuentaUsuario cuentaUsuario){
        CuentaUsuarioEntity cuentaUsuarioEntity = new CuentaUsuarioEntity();

        cuentaUsuarioEntity.setId(cuentaUsuario.getIdCuentaUsuario());
        cuentaUsuarioEntity.setCorreo(cuentaUsuario.getCorreo());
        cuentaUsuarioEntity.setContrasenia(cuentaUsuario.getContrasenia());
        cuentaUsuarioEntity.setRegistered(cuentaUsuario.getRegistered());
        cuentaUsuarioEntity.setUpdated(cuentaUsuario.getUpdated());

        return cuentaUsuarioEntity;
    }
}
