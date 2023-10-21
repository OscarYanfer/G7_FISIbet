package com.fisibet.gestionUsuarios.infraestructure.out.persistence.BilleteraVirtualPersistence;

import com.fisibet.gestionUsuarios.application.port.out.BilleteraVirtual.LoadBilleteraVirtualPort;
import com.fisibet.gestionUsuarios.application.port.out.BilleteraVirtual.SaveBillteraVirtualPort;
import com.fisibet.gestionUsuarios.application.port.out.BilleteraVirtual.UpdateBilleteraVirtualPort;
import com.fisibet.gestionUsuarios.domain.BilleteraVirtual;
import com.fisibet.gestionUsuarios.infraestructure.common.PersistenceAdapter;

@PersistenceAdapter
public class BilleteraVirtualPersistenceAdapter implements LoadBilleteraVirtualPort, SaveBillteraVirtualPort, UpdateBilleteraVirtualPort {

    private final SpringBilleteraVirtualRepository repository;

    public BilleteraVirtualPersistenceAdapter(SpringBilleteraVirtualRepository repository) {
        this.repository = repository;
    }

    @Override
    public BilleteraVirtual loadById(int id) {
        BilleteraVirtualEntity entity = repository.findByIdBilleteraVirtual(id);

        if(entity == null){
            return null;
        }

        return BilleteraVirtualMapper.entityToDomain(entity);
    }

    @Override
    public void save(BilleteraVirtual billeteraVirtual) {
        repository.save(BilleteraVirtualMapper.domainToEntity(billeteraVirtual));
    }

    @Override
    public void update(BilleteraVirtual billeteraVirtual) {
        repository.save(BilleteraVirtualMapper.domainToEntity(billeteraVirtual));
    }
}
