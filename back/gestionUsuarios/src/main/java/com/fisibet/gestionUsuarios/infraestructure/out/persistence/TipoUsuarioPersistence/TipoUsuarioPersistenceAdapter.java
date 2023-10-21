package com.fisibet.gestionUsuarios.infraestructure.out.persistence.TipoUsuarioPersistence;

import com.fisibet.gestionUsuarios.application.port.in.TipoUsuario.RegistrarTipoUsuarioCommand;
import com.fisibet.gestionUsuarios.application.port.in.TipoUsuario.RegistrarTipoUsuarioPort;
import com.fisibet.gestionUsuarios.application.port.out.TipoUsuario.LoadTipoUsuarioPort;
import com.fisibet.gestionUsuarios.application.port.out.TipoUsuario.SaveTipoUsuarioPort;
import com.fisibet.gestionUsuarios.application.port.out.TipoUsuario.UpdatTipoUsuarioPort;
import com.fisibet.gestionUsuarios.domain.TipoUsuario;
import com.fisibet.gestionUsuarios.infraestructure.common.PersistenceAdapter;
import com.fisibet.gestionUsuarios.infraestructure.out.persistence.CuentaUsuarioPersistence.CuentaUsuarioEntity;
import com.fisibet.gestionUsuarios.infraestructure.out.persistence.CuentaUsuarioPersistence.CuentaUsuarioMapper;
import com.fisibet.gestionUsuarios.infraestructure.out.persistence.CuentaUsuarioPersistence.SpringCuentaUsuarioRepository;

@PersistenceAdapter
public class TipoUsuarioPersistenceAdapter implements LoadTipoUsuarioPort, SaveTipoUsuarioPort, UpdatTipoUsuarioPort {

    private final SpringTipoUsuarioRepository repository;

    public TipoUsuarioPersistenceAdapter(SpringTipoUsuarioRepository repository) {
        this.repository = repository;
    }


    @Override
    public TipoUsuario loadById(int id) {
        TipoUsuarioEntity entity = repository.findById(id);

        if(entity == null){
            return null;
        } else {

        }

        return TipoUsuarioMapper.entityToDomain(entity);
    }

    @Override
    public void save(TipoUsuario tipoUsuario) {
        repository.save(TipoUsuarioMapper.domainToEntity(tipoUsuario));
    }


    @Override
    public void update(TipoUsuario tipoUsuario) {

    }
}
