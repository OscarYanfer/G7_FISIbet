package com.microservicio.fisibet.aplication.controller;

import com.microservicio.fisibet.aplication.dto.CreateBetDto;
import com.microservicio.fisibet.aplication.dto.GetBetDto;
import com.microservicio.fisibet.aplication.mapper.BetMapper;
import com.microservicio.fisibet.aplication.port.ConnectionPort;
import com.microservicio.fisibet.aplication.request.CreateBetRequest;
import com.microservicio.fisibet.aplication.response.BaseResponse;
import com.microservicio.fisibet.aplication.response.CreateBetResponse;
import com.microservicio.fisibet.aplication.response.GetBetResponse;
import com.microservicio.fisibet.aplication.usecase.CreateBetUseCase;
import com.microservicio.fisibet.aplication.usecase.GetBetsUseCase;
import com.microservicio.fisibet.domain.exception.GenericException;
import com.microservicio.fisibet.infraestructure.port.BetMySQLPort;
import com.microservicio.fisibet.infraestructure.port.ConnectionMySQLPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/bet")
public class BetController {
    private BetMapper betMapper;
    private ConnectionMySQLPort connectionMySQLPort;
    private BetMySQLPort betMySQLPort;
    public BetController(BetMapper betMapper,
                               BetMySQLPort betMySQLPort,
                               ConnectionMySQLPort connectionMySQLPort){
        this.betMapper = betMapper;
        this.connectionMySQLPort = connectionMySQLPort;
        this.betMySQLPort = betMySQLPort;
    }

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BaseResponse<CreateBetResponse>> createBet(
            @Validated @RequestBody CreateBetRequest request
    ) throws GenericException, IOException {

        CreateBetDto createBetDto = this.betMapper.convertCreateBetRequestToCreateBetDto(request);
        CreateBetUseCase createBetUseCase = new CreateBetUseCase(connectionMySQLPort, betMapper, betMySQLPort);

        createBetDto = createBetUseCase.run(createBetDto);

        CreateBetResponse createBetResponse = this.betMapper.convertCreateBetDtoToCreateBetResponse(createBetDto);
        BaseResponse<CreateBetResponse> response = new BaseResponse();
        response.setMessage("Se ha creado exitosamente la apuesta");
        response.setContent(createBetResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(path = "/")
    public @ResponseBody ResponseEntity<BaseResponse<List<GetBetResponse>>> getBets() throws GenericException {
        GetBetsUseCase getBetsUseCase = new GetBetsUseCase(connectionMySQLPort, betMySQLPort, betMapper);
        List<GetBetDto> getBetDtos = getBetsUseCase.run();

        List<GetBetResponse> getBetResponses = this.betMapper.convertGetBetDtosToGetBetResponses(getBetDtos);

        BaseResponse response = new BaseResponse();
        response.setMessage("Lista de eventos");
        response.setContent(getBetResponses);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
