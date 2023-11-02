package com.microservicio.fisibet.infraestructure.mapper;

import com.microservicio.fisibet.domain.entity.TransactionEntity;
import com.microservicio.fisibet.infraestructure.model.TransactionModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionInfraMapper {
    TransactionModel convertTransactionEntityToTransactionModel(TransactionEntity transactionEntity);
    TransactionEntity convertTransactionModelToTransactionEntity(TransactionModel transactionModel);
}
