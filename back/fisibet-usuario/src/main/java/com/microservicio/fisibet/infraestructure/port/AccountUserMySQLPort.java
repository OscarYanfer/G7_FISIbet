package com.microservicio.fisibet.infraestructure.port;

import com.microservicio.fisibet.aplication.port.AccountUserPort;
import com.microservicio.fisibet.domain.entity.AccountUserEntity;
import com.microservicio.fisibet.domain.entity.AccountUserEventEntity;
import com.microservicio.fisibet.infraestructure.mapper.AccountUserInfraMapper;
import com.microservicio.fisibet.infraestructure.model.*;
import com.microservicio.fisibet.infraestructure.port.spring.AccountUserSpringPort;
import com.microservicio.fisibet.infraestructure.port.spring.SessionUserSpringPort;
import com.microservicio.fisibet.infraestructure.port.spring.WalletSpringPort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountUserMySQLPort implements AccountUserPort {

    private AccountUserSpringPort accountUserSpringPort;
    private AccountUserInfraMapper accountUserInfraMapper;
    private KafkaTemplate<String, Object> kafkaTemplate;
    private WalletSpringPort walletSpringPort;
    private SessionUserSpringPort sessionUserSpringPort;

    public AccountUserMySQLPort(AccountUserSpringPort accountUserSpringPort,
                                AccountUserInfraMapper accountUserInfraMapper,
                                KafkaTemplate kafkaTemplate,
                                WalletSpringPort walletSpringPort,
                                SessionUserSpringPort sessionUserSpringPort){
        this.accountUserInfraMapper = accountUserInfraMapper;
        this.accountUserSpringPort = accountUserSpringPort;
        this.kafkaTemplate = kafkaTemplate;
        this.walletSpringPort = walletSpringPort;
        this.sessionUserSpringPort = sessionUserSpringPort;
    }

    @Override
    public AccountUserEntity createAccountUser(AccountUserEntity accountUserEntity) {
        accountUserEntity.setStatus(1);
        accountUserEntity.setRegisteredOn(LocalDateTime.now());
        AccountUserModel accountUserModelNew = this.accountUserSpringPort.save(this.accountUserInfraMapper.convertAccountUserEntityToAccountUserModel(accountUserEntity));
        WalletModel walletModel = new WalletModel();
        walletModel.setAccountNumber(String.valueOf(200000+accountUserModelNew.getId()));
        walletModel.setState(1);
        walletModel.setRegisteredOn(LocalDateTime.now());

        SessionUserModel sessionUserModel = new SessionUserModel();
        sessionUserModel.setId(accountUserModelNew.getId());
        sessionUserModel.setUsername(accountUserModelNew.getUsername());
        sessionUserModel.setEmail(accountUserModelNew.getEmail());
        sessionUserModel.setPassword(accountUserEntity.getPassword());
        sessionUserModel.setConectado(0);
        sessionUserModel.setProfile("cliente");
        sessionUserModel.setRegisteredOn(accountUserModelNew.getRegisteredOn());

        SessionUserModel sessionUserModel1 = this.sessionUserSpringPort.save(sessionUserModel);

        AccountUserEvent accountUserEvent1 = new AccountUserEvent("CreateAccountUser", accountUserModelNew);
        WalletModel walletModel1 = this.walletSpringPort.save(walletModel);
        accountUserModelNew.setWallet(walletModel1);
        kafkaTemplate.send("account-user-topic", accountUserEvent1);

        WalletEvent walletEvent = new WalletEvent("CreateWallet", walletModel1);
        kafkaTemplate.send("wallet-topic", walletEvent);
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
