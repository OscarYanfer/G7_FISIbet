package com.fisibet.gestionUsuarios.infraestructure.out.persistence.BonoPersistence;

import com.fisibet.gestionUsuarios.domain.Bono;

public class BonoMapper {
    public static Bono entityToDomain(BonoEntity entity){
        Bono domain = new Bono();

        domain.setIdBono(entity.getIdBono());
        domain.setNombreBono(entity.getNombreBono());
        domain.setEstado(entity.getEstado());
        domain.setRegistered(entity.getRegistered());
        domain.setUpdated(entity.getUpdated());

        return domain;
    }

    public static BonoEntity domainToEntity(Bono domain){
        BonoEntity entity = new BonoEntity();

        entity.setIdBono(domain.getIdBono());
        entity.setNombreBono(domain.getNombreBono());
        entity.setEstado(domain.getEstado());
        entity.setRegistered(domain.getRegistered());
        entity.setUpdated(domain.getUpdated());

        return entity;
    }
}
