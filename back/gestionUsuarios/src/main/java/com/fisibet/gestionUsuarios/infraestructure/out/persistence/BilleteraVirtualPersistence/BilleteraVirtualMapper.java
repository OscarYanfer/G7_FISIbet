package com.fisibet.gestionUsuarios.infraestructure.out.persistence.BilleteraVirtualPersistence;

import com.fisibet.gestionUsuarios.domain.BilleteraVirtual;

public class BilleteraVirtualMapper {

    public static BilleteraVirtual entityToDomain(BilleteraVirtualEntity entity){
        BilleteraVirtual domain = new BilleteraVirtual();

        domain.setIdBillteraVirtual(entity.getIdBilleteraVirtual());
        domain.setSaldo(entity.getSaldo());
        domain.setRegistered(entity.getRegistered());
        domain.setUpdated(entity.getUpdated());

        return domain;
    }

    public static BilleteraVirtualEntity domainToEntity(BilleteraVirtual domain){
        BilleteraVirtualEntity entity = new BilleteraVirtualEntity();

        entity.setIdBilleteraVirtual(domain.getIdBillteraVirtual());
        entity.setSaldo(domain.getSaldo());
        entity.setRegistered(domain.getRegistered());
        entity.setUpdated(domain.getUpdated());

        return entity;
    }
}
