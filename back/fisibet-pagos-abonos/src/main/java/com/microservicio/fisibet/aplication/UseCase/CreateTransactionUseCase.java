package com.microservicio.fisibet.aplication.UseCase;

import com.microservicio.fisibet.aplication.Controller.CreateTransaction;
import com.microservicio.fisibet.aplication.Dto.CreateTransactionDto;
import com.microservicio.fisibet.aplication.Dto.TransactionDto;
import com.microservicio.fisibet.aplication.mapper.TransactionMapper;
import com.microservicio.fisibet.aplication.port.ConnectionPort;
import com.microservicio.fisibet.aplication.port.TransactionPort;
import com.microservicio.fisibet.domain.entity.TransactionEntity;
import com.microservicio.fisibet.domain.exception.ErrorLevel;
import com.microservicio.fisibet.domain.exception.ErrorStatus;
import com.microservicio.fisibet.domain.exception.GenericException;
import com.microservicio.fisibet.infraestructure.mapper.TransactionInfraMapper;

public class CreateTransactionUseCase {
    private ConnectionPort connectionPort;
    private TransactionMapper transactionMapper;
    private TransactionPort transactionPort;
    public CreateTransactionUseCase(ConnectionPort connectionPort,
    TransactionMapper transactionMapper, TransactionPort transactionPort
                                    ){
        this.connectionPort = connectionPort;
        this.transactionMapper = transactionMapper;
        this.transactionPort = transactionPort;
    }

    public TransactionDto run(CreateTransactionDto request) throws GenericException {
        try{
            this.connectionPort.begin();
            TransactionDto transactionDto = createTransaction(request);

            this.connectionPort.commit();
            return transactionDto;
        }catch (Exception ex){
            this.connectionPort.rollback();
            throw new GenericException(
                    2,
                    ErrorStatus.UNAUTHORIZED,
                    ErrorLevel.ERROR,
                    ex.getMessage(),
                    "Ocurrió un error al crear la apuesta. Por favor, comunícate con el administrador"
            );
        } finally {
            this.connectionPort.close();
        }
    }

    public TransactionDto createTransaction(CreateTransactionDto request){
        TransactionEntity transactionEntity = this.transactionMapper.convertCreateTransactionDtoToTransactionEntity(request);
        TransactionEntity transactionEntity1 = this.transactionPort.createTransaction(transactionEntity);
        return this.transactionMapper.convertTransactionEntityToTransactionDto(transactionEntity1);
    }
}
