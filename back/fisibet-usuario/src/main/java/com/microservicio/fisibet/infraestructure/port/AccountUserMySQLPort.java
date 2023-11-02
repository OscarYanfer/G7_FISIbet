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
    public AccountUserEntity createAccountUser(AccountUserEventEntity accountUserEventEntity) {
        accountUserEventEntity.getAccountUser().setStatus(1);
        accountUserEventEntity.getAccountUser().setRegisteredOn(LocalDateTime.now());
        AccountUserEvent accountUserEvent = this.accountUserInfraMapper.convertAccountUserEventEntityToAccountUserEvent(accountUserEventEntity);
        AccountUserModel accountUserModelNew = this.accountUserSpringPort.save(accountUserEvent.getAccountUser());
        AccountUserEvent accountUserEvent1 = new AccountUserEvent(accountUserEventEntity.getEventType(), accountUserModelNew);

        kafkaTemplate.send("account-user-topic", accountUserEvent1);

        return this.accountUserInfraMapper.convertAccountUserModelToAccountUserEntity(accountUserModelNew);
    }
}
