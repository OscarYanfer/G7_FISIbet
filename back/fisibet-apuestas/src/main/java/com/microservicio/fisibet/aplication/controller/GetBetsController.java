package com.microservicio.fisibet.aplication.controller;

import com.microservicio.fisibet.aplication.dto.GetBetDto;
import com.microservicio.fisibet.aplication.mapper.BetMapper;
import com.microservicio.fisibet.aplication.response.BaseResponse;
import com.microservicio.fisibet.aplication.response.GetBetResponse;
import com.microservicio.fisibet.aplication.usecase.GetBetsUseCase;
import com.microservicio.fisibet.domain.exception.GenericException;
import com.microservicio.fisibet.infraestructure.port.BetMySQLPort;
import com.microservicio.fisibet.infraestructure.port.ConnectionMySQLPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(path = "/bet")
public class GetBetsController {
    private ConnectionMySQLPort connectionMySQLPort;
    private BetMapper betMapper;
    private BetMySQLPort betMySQLPort;
    public GetBetsController(ConnectionMySQLPort connectionMySQLPort,
                             BetMapper betMapper,
                             BetMySQLPort betMySQLPort){
        this.connectionMySQLPort = connectionMySQLPort;
        this.betMapper = betMapper;
        this.betMySQLPort = betMySQLPort;
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
