package com.fisibet.gestionUsuarios.infraestructure.out.persistence.UsuarioPersistence;

import com.fisibet.gestionUsuarios.domain.Usuario;

public class UsuarioMapper {

    public static Usuario entityToDomain(UsuarioEntity entity) {
        Usuario domain = new Usuario();

        domain.setIdUsuario(entity.getIdUsuario());
        domain.setNombreUsuario(entity.getNombreUsuario());
        domain.setDni(entity.getDni());
        domain.setRegistered(entity.getRegisted());
        domain.setUpdated(entity.getUpdated());

        return domain;
    }

    public static UsuarioEntity domainToEntity(Usuario domain){
        UsuarioEntity entity = new UsuarioEntity();

        entity.setIdUsuario(domain.getIdUsuario());
        entity.setNombreUsuario(domain.getNombreUsuario());
        entity.setDni(domain.getDni());
        entity.setRegisted(domain.getRegistered());
        entity.setUpdated(domain.getUpdated());

        return entity;
    }

}
