package com.fisibet.gestionUsuarios;

import com.fisibet.gestionUsuarios.infraestructure.out.persistence.BilleteraVirtualPersistence.BilleteraVirtualEntity;
import com.fisibet.gestionUsuarios.infraestructure.out.persistence.BilleteraVirtualPersistence.SpringBilleteraVirtualRepository;
import com.fisibet.gestionUsuarios.infraestructure.out.persistence.BonoPersistence.BonoEntity;
import com.fisibet.gestionUsuarios.infraestructure.out.persistence.BonoPersistence.SpringBonoRepository;
import com.fisibet.gestionUsuarios.infraestructure.out.persistence.CuentaUsuarioPersistence.CuentaUsuarioEntity;
import com.fisibet.gestionUsuarios.infraestructure.out.persistence.CuentaUsuarioPersistence.SpringCuentaUsuarioRepository;
import com.fisibet.gestionUsuarios.infraestructure.out.persistence.TarjetaPersistence.SpringTarjetaRepository;
import com.fisibet.gestionUsuarios.infraestructure.out.persistence.TarjetaPersistence.TarjetaEntity;
import com.fisibet.gestionUsuarios.infraestructure.out.persistence.TipoUsuarioPersistence.SpringTipoUsuarioRepository;
import com.fisibet.gestionUsuarios.infraestructure.out.persistence.TipoUsuarioPersistence.TipoUsuarioEntity;
import com.fisibet.gestionUsuarios.infraestructure.out.persistence.UsuarioPersistence.SpringUsuarioRepository;
import com.fisibet.gestionUsuarios.infraestructure.out.persistence.UsuarioPersistence.UsuarioEntity;
import org.springframework.beans.factory.InitializingBean;

public class Extra implements InitializingBean {
    private final SpringCuentaUsuarioRepository extra;
    private final SpringTipoUsuarioRepository extra1;
    private final SpringBilleteraVirtualRepository extra3;
    private final SpringBonoRepository extra4;
    private final SpringTarjetaRepository extra5;
    private final SpringUsuarioRepository extra6;

    public Extra(SpringCuentaUsuarioRepository extra, SpringTipoUsuarioRepository extra1, SpringBilleteraVirtualRepository extra3, SpringBonoRepository extra4, SpringTarjetaRepository extra5, SpringUsuarioRepository extra6) {
        this.extra = extra;
        this.extra1 = extra1;
        this.extra3 = extra3;
        this.extra4 = extra4;
        this.extra5 = extra5;
        this.extra6 = extra6;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        this.extra.save(new CuentaUsuarioEntity(1, "correo@prueba.com", "contraseña", "21/10/2023", "21/10/2023"));
        this.extra1.save(new TipoUsuarioEntity(1, "Administrador", "21/10/2023", "21/10/2023"));
        this.extra3.save(new BilleteraVirtualEntity());
        this.extra4.save(new BonoEntity());
        this.extra5.save(new TarjetaEntity());
        this.extra6.save(new UsuarioEntity());

    }
}
