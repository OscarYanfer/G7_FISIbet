package com.microservicio.fisibet.aplication.Controller;

import com.microservicio.fisibet.aplication.Dto.CreateTransactionDto;
import com.microservicio.fisibet.aplication.Dto.TransactionDto;
import com.microservicio.fisibet.aplication.UseCase.CreateTransactionUseCase;
import com.microservicio.fisibet.aplication.mapper.TransactionMapper;
import com.microservicio.fisibet.aplication.request.CreateTransactionRequest;
import com.microservicio.fisibet.aplication.response.BaseResponse;
import com.microservicio.fisibet.aplication.response.CreateTransactionResponse;
import com.microservicio.fisibet.aplication.response.TransactionResponse;
import com.microservicio.fisibet.domain.exception.GenericException;
import com.microservicio.fisibet.infraestructure.port.ConnectionMySQLPort;
import com.microservicio.fisibet.infraestructure.port.TransactionMySQLPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping(path = "/transaction")
public class CreateTransaction {
    @Autowired
    private ConnectionMySQLPort connectionMySQLPort;
    @Autowired
    private TransactionMapper transactionMapper;
    @Autowired
    private TransactionMySQLPort transactionMySQLPort;
    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BaseResponse<TransactionResponse>> createAccountUser(
            @Validated @RequestBody CreateTransactionRequest request
    ) throws GenericException, IOException {

        CreateTransactionDto createTransactionDto = this.transactionMapper.convertCreateTransactionRequestToCreateTransactionDto(request);
        CreateTransactionUseCase createTransactionUseCase = new CreateTransactionUseCase(connectionMySQLPort, transactionMapper, transactionMySQLPort);

        TransactionDto transactionDto = createTransactionUseCase.run(createTransactionDto);

        TransactionResponse transactionResponse = this.transactionMapper.convertTransactionDtoToTransactionResponse(transactionDto);
        BaseResponse<TransactionResponse> response = new BaseResponse();
        response.setMessage("Se ha creado exitosamente la apuesta");
        response.setContent(transactionResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
