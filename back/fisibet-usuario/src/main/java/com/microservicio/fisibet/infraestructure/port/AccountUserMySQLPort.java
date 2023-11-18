package com.microservicio.fisibet.infraestructure.port;

import com.microservicio.fisibet.aplication.port.AccountUserPort;
import com.microservicio.fisibet.domain.entity.AccountUserEntity;
import com.microservicio.fisibet.domain.entity.AccountUserEventEntity;
import com.microservicio.fisibet.infraestructure.mapper.AccountUserInfraMapper;
import com.microservicio.fisibet.infraestructure.model.AccountUserEvent;
import com.microservicio.fisibet.infraestructure.model.AccountUserModel;
import com.microservicio.fisibet.infraestructure.port.spring.AccountUserSpringPort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountUserMySQLPort implements AccountUserPort {

    private AccountUserSpringPort accountUserSpringPort;
    private AccountUserInfraMapper accountUserInfraMapper;
    private KafkaTemplate<String, Object> kafkaTemplate;

    public AccountUserMySQLPort(AccountUserSpringPort accountUserSpringPort,
                                AccountUserInfraMapper accountUserInfraMapper,
                                KafkaTemplate kafkaTemplate){
        this.accountUserInfraMapper = accountUserInfraMapper;
        this.accountUserSpringPort = accountUserSpringPort;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public AccountUserEntity createAccountUser(AccountUserEntity accountUserEntity) {
        accountUserEntity.setStatus(1);
        accountUserEntity.setRegisteredOn(LocalDateTime.now());
        AccountUserModel accountUserModelNew = this.accountUserSpringPort.save(this.accountUserInfraMapper.convertAccountUserEntityToAccountUserModel(accountUserEntity));
        AccountUserEvent accountUserEvent1 = new AccountUserEvent("CreateAccountUser", accountUserModelNew);
        kafkaTemplate.send("account-user-topic", accountUserEvent1);
        return this.accountUserInfraMapper.convertAccountUserModelToAccountUserEntity(accountUserModelNew);
    }

    @Override
    public AccountUserEntity getAccountUserById(Integer id) {
        AccountUserModel accountUserModel = this.accountUserSpringPort.findById(id).orElse(null);
        return this.accountUserInfraMapper.convertAccountUserModelToAccountUserEntity(accountUserModel);
    }

    @Override
    public List<AccountUserEntity> getAccountUsers() {
        List<AccountUserModel> accountUserModels = this.accountUserSpringPort.findAll();
        return this.accountUserInfraMapper.convertAccountUserModelsToAccountUserEntities(accountUserModels);
    }

    @Override
    public AccountUserEntity updateAccountUserById(AccountUserEntity accountUserEntity, Integer id) {
        AccountUserModel accountUserModel = this.accountUserSpringPort.findById(id).orElse(null);
        accountUserModel.setId(id);
        accountUserModel.setDni(accountUserEntity.getDni());
        accountUserModel.setEmail(accountUserEntity.getEmail());
        accountUserModel.setPassword(accountUserEntity.getPassword());
        accountUserModel.setUsername(accountUserEntity.getUsername());
        accountUserModel.setStatus(1);
        accountUserModel.setUpdatedOn(LocalDateTime.now());
        AccountUserModel accountUserModel1 = this.accountUserSpringPort.save(accountUserModel);

        AccountUserEvent accountUserEvent = new AccountUserEvent("UpdateAccountUser", accountUserModel1);

        kafkaTemplate.send("account-user-topic", accountUserEvent);

        return this.accountUserInfraMapper.convertAccountUserModelToAccountUserEntity(accountUserModel1);
    }
}
