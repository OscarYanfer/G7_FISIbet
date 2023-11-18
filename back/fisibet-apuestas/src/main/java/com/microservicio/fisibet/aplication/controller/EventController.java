package com.microservicio.fisibet.aplication.controller;

import com.microservicio.fisibet.aplication.dto.CreateBetDto;
import com.microservicio.fisibet.aplication.dto.CreateEventDto;
import com.microservicio.fisibet.aplication.dto.GetEventDto;
import com.microservicio.fisibet.aplication.mapper.BetMapper;
import com.microservicio.fisibet.aplication.mapper.EventMapper;
import com.microservicio.fisibet.aplication.port.ConnectionPort;
import com.microservicio.fisibet.aplication.request.CreateBetRequest;
import com.microservicio.fisibet.aplication.request.CreateEventRequest;
import com.microservicio.fisibet.aplication.response.BaseResponse;
import com.microservicio.fisibet.aplication.response.CreateBetResponse;
import com.microservicio.fisibet.aplication.response.CreateEventResponse;
import com.microservicio.fisibet.aplication.response.GetEventResponse;
import com.microservicio.fisibet.aplication.usecase.CreateBetUseCase;
import com.microservicio.fisibet.aplication.usecase.CreateEventUseCase;
import com.microservicio.fisibet.aplication.usecase.GetEventsUseCase;
import com.microservicio.fisibet.domain.exception.GenericException;
import com.microservicio.fisibet.infraestructure.port.BetMySQLPort;
import com.microservicio.fisibet.infraestructure.port.ConnectionMySQLPort;
import com.microservicio.fisibet.infraestructure.port.EventMySQLPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/event")
public class EventController {
    private EventMapper eventMapper;
    private ConnectionMySQLPort connectionMySQLPort;
    private EventMySQLPort eventMySQLPort;
    public EventController(
            EventMapper eventMapper,
            ConnectionMySQLPort connectionMySQLPort,
            EventMySQLPort eventMySQLPort
    ){
        this.eventMapper = eventMapper;
        this.connectionMySQLPort = connectionMySQLPort;
        this.eventMySQLPort = eventMySQLPort;
    }

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE,
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

    @GetMapping(path = "/all")
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
