package com.fisibet.gestionUsuarios.infraestructure.out.persistence.CuentaUsuarioPersistence;

import com.fisibet.gestionUsuarios.application.port.out.CuentaUsuario.LoadCuentaUsuarioPort;
import com.fisibet.gestionUsuarios.application.port.out.CuentaUsuario.SaveCuentaUsuarioPort;
import com.fisibet.gestionUsuarios.application.port.out.CuentaUsuario.UpdateCuentaUsuarioPort;
import com.fisibet.gestionUsuarios.domain.CuentaUsuario;
import com.fisibet.gestionUsuarios.infraestructure.common.PersistenceAdapter;

@PersistenceAdapter
public class CuentaUsuarioPersistenceAdapter implements LoadCuentaUsuarioPort, UpdateCuentaUsuarioPort, SaveCuentaUsuarioPort {

    private final SpringCuentaUsuarioRepository cuentaUsuarioRepository;

    public CuentaUsuarioPersistenceAdapter(SpringCuentaUsuarioRepository cuentaUsuarioRepository) {
        this.cuentaUsuarioRepository = cuentaUsuarioRepository;
    }

    @Override
    public CuentaUsuario loadByCorreo(String correo) {
        CuentaUsuarioEntity entity = cuentaUsuarioRepository.findByCorreo(correo);

        if(entity == null){
            return null;
        } else {

        }

        return CuentaUsuarioMapper.entityToDomain(entity);

    }

    @Override
    public void updateCuentaUsuario(CuentaUsuario cuentaUsuario) {
        cuentaUsuarioRepository.save(CuentaUsuarioMapper.domainToEntity(cuentaUsuario));
    }

    @Override
    public void save(CuentaUsuario cuentaUsuario){
        cuentaUsuarioRepository.save(CuentaUsuarioMapper.domainToEntity(cuentaUsuario));

    }
}
