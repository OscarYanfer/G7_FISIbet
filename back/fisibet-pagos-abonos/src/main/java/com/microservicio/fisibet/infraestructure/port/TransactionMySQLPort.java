package com.microservicio.fisibet.infraestructure.port;

import com.microservicio.fisibet.aplication.port.TransactionPort;
import com.microservicio.fisibet.domain.entity.TransactionEntity;
import com.microservicio.fisibet.infraestructure.mapper.TransactionInfraMapper;
import com.microservicio.fisibet.infraestructure.model.TransactionModel;
import com.microservicio.fisibet.infraestructure.port.spring.TransactionSpringPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionMySQLPort implements TransactionPort {
    @Autowired
    private TransactionSpringPort transactionSpringPort;
    @Autowired
    private TransactionInfraMapper transactionInfraMapper;
    @Override
    public TransactionEntity createTransaction(TransactionEntity transactionEntity) {
        transactionEntity.setStatus(1);
        transactionEntity.setRegisteredOn(LocalDateTime.now());
        TransactionModel transactionModel = this.transactionInfraMapper.convertTransactionEntityToTransactionModel(transactionEntity);
        TransactionModel transactionModel1 = this.transactionSpringPort.save(transactionModel);
        return this.transactionInfraMapper.convertTransactionModelToTransactionEntity(transactionModel1);
    }
}
