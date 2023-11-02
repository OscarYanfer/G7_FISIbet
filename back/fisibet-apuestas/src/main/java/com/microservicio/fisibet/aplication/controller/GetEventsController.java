package com.microservicio.fisibet.aplication.controller;

import com.microservicio.fisibet.aplication.dto.GetEventDto;
import com.microservicio.fisibet.aplication.mapper.EventMapper;
import com.microservicio.fisibet.aplication.response.BaseResponse;
import com.microservicio.fisibet.aplication.response.GetEventResponse;
import com.microservicio.fisibet.aplication.usecase.GetEventsUseCase;
import com.microservicio.fisibet.domain.exception.GenericException;
import com.microservicio.fisibet.infraestructure.port.ConnectionMySQLPort;
import com.microservicio.fisibet.infraestructure.port.EventMySQLPort;
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
