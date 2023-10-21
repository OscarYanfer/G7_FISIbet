package com.fisibet.gestionUsuarios.infraestructure.out.persistence.TarjetaPersistence;

import com.fisibet.gestionUsuarios.application.port.out.Tarjeta.LoadTarjetaPort;
import com.fisibet.gestionUsuarios.application.port.out.Tarjeta.SaveTarjetaPort;
import com.fisibet.gestionUsuarios.application.port.out.Tarjeta.UpdateTarjetaPort;
import com.fisibet.gestionUsuarios.domain.Tarjeta;
import com.fisibet.gestionUsuarios.infraestructure.common.PersistenceAdapter;

@PersistenceAdapter
public class TarjetaPersistenceAdapter implements LoadTarjetaPort, SaveTarjetaPort, UpdateTarjetaPort {

    private final SpringTarjetaRepository repository;

    public TarjetaPersistenceAdapter(SpringTarjetaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Tarjeta load(int id) {
        TarjetaEntity entity = repository.findByIdTarjeta(id);

        if(entity == null){
            return null;
        }
        return TarjetaMapper.entityToDomain(entity);
    }

    @Override
    public void save(Tarjeta tarjeta) {
        repository.save(TarjetaMapper.domainToEntity(tarjeta));
    }

    @Override
    public void update(Tarjeta tarjeta) {
        repository.save(TarjetaMapper.domainToEntity(tarjeta));
    }
}
