package com.microservicio.fisibet.infraestructure.port;

import com.microservicio.fisibet.aplication.port.AccountUserPort;
import com.microservicio.fisibet.domain.entity.AccountUserEntity;
import com.microservicio.fisibet.infraestructure.mapper.AccountUserInfraMapper;
import com.microservicio.fisibet.infraestructure.model.AccountUserEvent;
import com.microservicio.fisibet.infraestructure.model.AccountUserModel;
import com.microservicio.fisibet.infraestructure.port.spring.AccountUserSpringPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AccountUserMySQLPort implements AccountUserPort {
    @Autowired
    private AccountUserSpringPort accountUserSpringPort;
    @Autowired
    private AccountUserInfraMapper accountUserInfraMapper;

    @Override
    public List<AccountUserEntity> getAccountUsers() {
        List< AccountUserModel > accountUserModels = this.accountUserSpringPort.findAll();
        return this.accountUserInfraMapper.convertAccountUserModelsToAccountUserEntities(accountUserModels);
    }

    @KafkaListener(topics = "account-user-topic", groupId = "Group1003",containerFactory = "apuestasListener")
    public void processProductEvents(AccountUserEvent accountUserEvent){
        AccountUserModel accountUserModel = accountUserEvent.getAccountUser();
        System.out.println("Recepcionando: "+accountUserModel.toString());
        if(accountUserEvent.getEventType().equals("CreateAccountUser")){
            this.accountUserSpringPort.save(accountUserModel);
            System.out.println("Creando: " + accountUserEvent.getEventType());
        }else if(accountUserEvent.getEventType().equals("UpdateAccountUser")){
            this.accountUserSpringPort.save(accountUserModel);
            System.out.println("Actualizando: " + accountUserEvent.getEventType());
        }
    }
}
