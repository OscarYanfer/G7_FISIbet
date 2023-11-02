package com.microservicio.fisibet.aplication.controller;

import com.microservicio.fisibet.aplication.dto.CreateEventDto;
import com.microservicio.fisibet.aplication.mapper.EventMapper;
import com.microservicio.fisibet.aplication.request.CreateEventRequest;
import com.microservicio.fisibet.aplication.response.BaseResponse;
import com.microservicio.fisibet.aplication.response.CreateEventResponse;
import com.microservicio.fisibet.aplication.usecase.CreateEventUseCase;
import com.microservicio.fisibet.domain.exception.GenericException;
import com.microservicio.fisibet.infraestructure.port.ConnectionMySQLPort;
import com.microservicio.fisibet.infraestructure.port.EventMySQLPort;
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
@RequestMapping(path = "/event")
public class CreateEventController {
    private EventMapper eventMapper;
    private ConnectionMySQLPort connectionMySQLPort;
    private EventMySQLPort eventMySQLPort;
    public CreateEventController(
            EventMapper eventMapper,
            ConnectionMySQLPort connectionMySQLPort,
            EventMySQLPort eventMySQLPort
    ){
        this.eventMapper = eventMapper;
        this.connectionMySQLPort = connectionMySQLPort;
        this.eventMySQLPort = eventMySQLPort;
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BaseResponse<CreateEventResponse>> createEvent(
            @Validated @RequestBody CreateEventRequest request
    ) throws GenericException, IOException {

        CreateEventDto createEventDto = this.eventMapper.convertCreateEventRequestToCreateEventDto(request);
        CreateEventUseCase createEventUseCase = new CreateEventUseCase(connectionMySQLPort, eventMapper, eventMySQLPort);
        createEventDto = createEventUseCase.run(createEventDto);

        CreateEventResponse createEventResponse = this.eventMapper.convertCreateEventDtoToCreateEventResponse(createEventDto);
        BaseResponse<CreateEventResponse> response = new BaseResponse();
        response.setMessage("Se ha creado exitosamente el evento");
        response.setContent(createEventResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
