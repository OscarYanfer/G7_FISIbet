package com.ms.gestionapuestas.aplication.controller;

import com.ms.gestionapuestas.aplication.dto.GetEventDto;
import com.ms.gestionapuestas.aplication.mapper.EventMapper;
import com.ms.gestionapuestas.aplication.response.BaseResponse;
import com.ms.gestionapuestas.aplication.response.GetEventResponse;
import com.ms.gestionapuestas.aplication.usecase.GetEventsUseCase;
import com.ms.gestionapuestas.domain.exception.GenericException;
import com.ms.gestionapuestas.infraestructure.port.ConnectionMySQLPort;
import com.ms.gestionapuestas.infraestructure.port.EventMySQLPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(path = "/event")
public class GetEventsController {
    private EventMapper eventMapper;
    private ConnectionMySQLPort connectionMySQLPort;
    private EventMySQLPort eventMySQLPort;
    public GetEventsController(
            EventMapper eventMapper,
            ConnectionMySQLPort connectionMySQLPort,
            EventMySQLPort eventMySQLPort
    ){
        this.eventMapper = eventMapper;
        this.connectionMySQLPort = connectionMySQLPort;
        this.eventMySQLPort = eventMySQLPort;
    }

    @GetMapping(path = "/")
    public @ResponseBody ResponseEntity<BaseResponse<List<GetEventResponse>>> getEvents() throws GenericException {
        GetEventsUseCase getEventsUseCase = new GetEventsUseCase(connectionMySQLPort, eventMySQLPort, eventMapper);
        List<GetEventDto> getEventDtos = getEventsUseCase.run();

        List<GetEventResponse> getEventResponses = this.eventMapper.convertGetEventDtoToGetEventResponse(getEventDtos);

        BaseResponse response = new BaseResponse();
        response.setMessage("Lista de eventos");
        response.setContent(getEventResponses);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
