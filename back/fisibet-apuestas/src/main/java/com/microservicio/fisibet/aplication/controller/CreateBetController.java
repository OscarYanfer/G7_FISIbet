package com.microservicio.fisibet.aplication.controller;

import com.microservicio.fisibet.aplication.dto.CreateBetDto;
import com.microservicio.fisibet.aplication.mapper.BetMapper;
import com.microservicio.fisibet.aplication.port.ConnectionPort;
import com.microservicio.fisibet.aplication.request.CreateBetRequest;
import com.microservicio.fisibet.aplication.response.BaseResponse;
import com.microservicio.fisibet.aplication.response.CreateBetResponse;
import com.microservicio.fisibet.aplication.usecase.CreateBetUseCase;
import com.microservicio.fisibet.domain.exception.GenericException;
import com.microservicio.fisibet.infraestructure.port.BetMySQLPort;
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
@RequestMapping(path = "/bet")
public class CreateBetController {
    private BetMapper betMapper;
    private ConnectionPort connectionPort;
    private BetMySQLPort betMySQLPort;
    public CreateBetController(BetMapper betMapper,
                               BetMySQLPort betMySQLPort,
                               ConnectionPort connectionPort){
        this.betMapper = betMapper;
        this.connectionPort = connectionPort;
        this.betMySQLPort = betMySQLPort;
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BaseResponse<CreateBetResponse>> createBet(
            @Validated @RequestBody CreateBetRequest request
    ) throws GenericException, IOException {

        CreateBetDto createBetDto = this.betMapper.convertCreateBetRequestToCreateBetDto(request);
        CreateBetUseCase createBetUseCase = new CreateBetUseCase(connectionPort, betMapper, betMySQLPort);

        createBetDto = createBetUseCase.run(createBetDto);

        CreateBetResponse createBetResponse = this.betMapper.convertCreateBetDtoToCreateBetResponse(createBetDto);
        BaseResponse<CreateBetResponse> response = new BaseResponse();
        response.setMessage("Se ha creado exitosamente la apuesta");
        response.setContent(createBetResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
