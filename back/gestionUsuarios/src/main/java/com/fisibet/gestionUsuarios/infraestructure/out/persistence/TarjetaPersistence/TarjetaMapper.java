package com.fisibet.gestionUsuarios.infraestructure.out.persistence.TarjetaPersistence;

import com.fisibet.gestionUsuarios.domain.Tarjeta;

public class TarjetaMapper {

    public static Tarjeta entityToDomain(TarjetaEntity entity){
        Tarjeta domain = new Tarjeta();
        domain.setIdTarjeta(entity.getIdTarjeta());
        domain.setNumero(entity.getNumero());
        domain.setCsv(entity.getCsv());
        domain.setFechaVencimiento(entity.getFechaVencimiento());
        domain.setRegistered(entity.getRegistered());
        domain.setUpdated(entity.getUpdated());
        return domain;
    }

    public static TarjetaEntity domainToEntity(Tarjeta domain){
        TarjetaEntity entity = new TarjetaEntity();
        entity.setIdTarjeta(domain.getIdTarjeta());
        entity.setNumero(domain.getNumero());
        entity.setCsv(domain.getCsv());
        entity.setFechaVencimiento(domain.getFechaVencimiento());
        entity.setRegistered(domain.getRegistered());
        entity.setUpdated(domain.getUpdated());
        return entity;
    }
}
