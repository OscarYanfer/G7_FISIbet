package com.fisibet.gestionUsuarios.infraestructure.out.persistence.UsuarioPersistence;

import com.fisibet.gestionUsuarios.application.port.out.Usuario.LoadUsuarioPort;
import com.fisibet.gestionUsuarios.application.port.out.Usuario.SaveUsuarioPort;
import com.fisibet.gestionUsuarios.application.port.out.Usuario.UpdateUsuarioPort;
import com.fisibet.gestionUsuarios.domain.Usuario;
import com.fisibet.gestionUsuarios.infraestructure.common.PersistenceAdapter;
import com.fisibet.gestionUsuarios.infraestructure.out.persistence.CuentaUsuarioPersistence.CuentaUsuarioEntity;

@PersistenceAdapter
public class UsuarioPersistenceAdapter implements LoadUsuarioPort, SaveUsuarioPort, UpdateUsuarioPort {

    private final SpringUsuarioRepository repository;

    public UsuarioPersistenceAdapter(SpringUsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public Usuario load(int id) {
        UsuarioEntity entity = repository.findById(id);
        if(entity==null){
            return null;
        } else {

        }
        return UsuarioMapper.entityToDomain(entity);
    }

    @Override
    public void save(Usuario usuario) {
        repository.save(UsuarioMapper.domainToEntity(usuario));
    }

    @Override
    public void update(Usuario usuario) {
        repository.save(UsuarioMapper.domainToEntity(usuario));
    }
}
