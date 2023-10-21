package com.ms.gestionapuestas.aplication.controller;

import com.ms.gestionapuestas.aplication.dto.GetBetDto;
import com.ms.gestionapuestas.aplication.dto.GetEventDto;
import com.ms.gestionapuestas.aplication.mapper.BetMapper;
import com.ms.gestionapuestas.aplication.response.BaseResponse;
import com.ms.gestionapuestas.aplication.response.GetBetResponse;
import com.ms.gestionapuestas.aplication.response.GetEventResponse;
import com.ms.gestionapuestas.aplication.usecase.GetBetsUseCase;
import com.ms.gestionapuestas.aplication.usecase.GetEventsUseCase;
import com.ms.gestionapuestas.domain.exception.GenericException;
import com.ms.gestionapuestas.infraestructure.port.BetMySQLPort;
import com.ms.gestionapuestas.infraestructure.port.ConnectionMySQLPort;
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
