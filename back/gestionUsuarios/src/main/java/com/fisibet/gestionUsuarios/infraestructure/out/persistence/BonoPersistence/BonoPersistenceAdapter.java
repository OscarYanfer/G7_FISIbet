package com.fisibet.gestionUsuarios.infraestructure.out.persistence.BonoPersistence;

import com.fisibet.gestionUsuarios.application.port.out.Bono.LoadBonoPort;
import com.fisibet.gestionUsuarios.application.port.out.Bono.SaveBonoPort;
import com.fisibet.gestionUsuarios.application.port.out.Bono.UpdateBonoPort;
import com.fisibet.gestionUsuarios.domain.Bono;
import com.fisibet.gestionUsuarios.infraestructure.common.PersistenceAdapter;

@PersistenceAdapter
public class BonoPersistenceAdapter implements LoadBonoPort, SaveBonoPort, UpdateBonoPort {

    private final SpringBonoRepository repository;

    public BonoPersistenceAdapter(SpringBonoRepository springBonoRepository) {
        this.repository = springBonoRepository;
    }

    @Override
    public Bono loadById(int id) {
        BonoEntity entity = repository.findByIdBono(id);

        if(entity == null){
            return null;
        }

        return BonoMapper.entityToDomain(entity);
    }

    @Override
    public void save(Bono bono) {
        repository.save(BonoMapper.domainToEntity(bono));
    }

    @Override
    public void update(Bono bono) {
        repository.save(BonoMapper.domainToEntity(bono));
    }
}
