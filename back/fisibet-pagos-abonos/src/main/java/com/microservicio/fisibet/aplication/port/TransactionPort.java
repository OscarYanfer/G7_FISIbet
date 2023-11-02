package com.microservicio.fisibet.aplication.port;

import com.microservicio.fisibet.domain.entity.TransactionEntity;

public interface TransactionPort {
    TransactionEntity createTransaction(TransactionEntity transactionEntity);
}
