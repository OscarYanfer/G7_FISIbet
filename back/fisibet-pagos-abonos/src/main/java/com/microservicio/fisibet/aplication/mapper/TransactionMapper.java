package com.microservicio.fisibet.aplication.mapper;

import com.microservicio.fisibet.aplication.Dto.CreateTransactionDto;
import com.microservicio.fisibet.aplication.Dto.TransactionDto;
import com.microservicio.fisibet.aplication.request.CreateTransactionRequest;
import com.microservicio.fisibet.aplication.response.TransactionResponse;
import com.microservicio.fisibet.domain.entity.TransactionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    CreateTransactionDto convertCreateTransactionRequestToCreateTransactionDto(CreateTransactionRequest createTransactionRequest);
    TransactionResponse convertTransactionDtoToTransactionResponse(TransactionDto transactionDto);
    TransactionEntity convertCreateTransactionDtoToTransactionEntity(CreateTransactionDto createTransactionDto);
    TransactionDto convertTransactionEntityToTransactionDto(TransactionEntity transactionEntity);
}
