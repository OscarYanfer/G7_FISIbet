package com.microservicio.fisibet.infraestructure.port;

import com.microservicio.fisibet.aplication.port.SessionUserPort;
import com.microservicio.fisibet.domain.entity.SessionUserEntity;
import com.microservicio.fisibet.infraestructure.mapper.SessionUserInfraMapper;
import com.microservicio.fisibet.infraestructure.model.AccountUserModel;
import com.microservicio.fisibet.infraestructure.model.SessionUserModel;
import com.microservicio.fisibet.infraestructure.port.spring.AccountUserSpringPort;
import com.microservicio.fisibet.infraestructure.port.spring.SessionUserSpringPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class SessionUserMySQLPort implements SessionUserPort {
    @Autowired
    private SessionUserInfraMapper sessionUserInfraMapper;
    @Autowired
    private SessionUserSpringPort sessionUserSpringPort;
    @Autowired
    private AccountUserSpringPort accountUserSpringPort;

    @Override
    public SessionUserEntity login(SessionUserEntity sessionUserEntity) {
        SessionUserModel sessionUserModel = this.sessionUserSpringPort.getSessionUserByName(sessionUserEntity.getUsername());
        if(sessionUserModel == null){
            AccountUserModel accountUserModel = this.accountUserSpringPort.getAccountUserByName(sessionUserEntity.getUsername());
            SessionUserModel sessionUserModel1 = this.sessionUserInfraMapper.convertAccountUserModelToSessionUserModel(accountUserModel);
            sessionUserModel1.setConectado(1);
            sessionUserModel1.setProfile("cliente");
            sessionUserModel1.setRegisteredOn(LocalDateTime.now());
            sessionUserModel1.setUpdatedOn(null);
            sessionUserModel = this.sessionUserSpringPort.save(sessionUserModel1);

            return this.sessionUserInfraMapper.convertSessionUserModelToSessionUserEntity(sessionUserModel);
        }
        if(sessionUserModel.getConectado().equals(1)){
            return null;
        }
        sessionUserModel.setConectado(1);
        sessionUserModel.setUpdatedOn(LocalDateTime.now());
        SessionUserModel sessionUserModel1 = this.sessionUserSpringPort.save(sessionUserModel);
        return this.sessionUserInfraMapper.convertSessionUserModelToSessionUserEntity(sessionUserModel1);
    }

    @Override
    public Integer disconnectUsers() {
        return this.sessionUserSpringPort.disconnectSessionUsers();
    }
}
