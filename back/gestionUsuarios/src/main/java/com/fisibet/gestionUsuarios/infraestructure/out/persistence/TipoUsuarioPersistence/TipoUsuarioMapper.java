package com.fisibet.gestionUsuarios.infraestructure.out.persistence.TipoUsuarioPersistence;

import com.fisibet.gestionUsuarios.domain.TipoUsuario;

public class TipoUsuarioMapper {

    public static TipoUsuario entityToDomain(TipoUsuarioEntity entity){
        TipoUsuario domain = new TipoUsuario();

        domain.setIdTipoUsuario(entity.getId());
        domain.setNombreTipoUsuario(entity.getNombreTipoUsuario());
        domain.setRegistered(entity.getRegistered());
        domain.setUpdated(entity.getUpdated());

        return domain;
    }

    public static TipoUsuarioEntity domainToEntity(TipoUsuario domain){
        TipoUsuarioEntity entity = new TipoUsuarioEntity();

        entity.setId(domain.getIdTipoUsuario());
        entity.setNombreTipoUsuario(domain.getNombreTipoUsuario());
        entity.setRegistered(domain.getRegistered());
        entity.setUpdated(domain.getUpdated());

        return entity;
    }

}
