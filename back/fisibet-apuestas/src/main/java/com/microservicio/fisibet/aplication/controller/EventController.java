package com.microservicio.fisibet.aplication.controller;

import com.microservicio.fisibet.aplication.dto.*;
import com.microservicio.fisibet.aplication.mapper.BetMapper;
import com.microservicio.fisibet.aplication.mapper.EventMapper;
import com.microservicio.fisibet.aplication.port.ConnectionPort;
import com.microservicio.fisibet.aplication.request.CreateBetRequest;
import com.microservicio.fisibet.aplication.request.CreateEventRequest;
import com.microservicio.fisibet.aplication.request.UpdateEventRequest;
import com.microservicio.fisibet.aplication.response.*;
import com.microservicio.fisibet.aplication.usecase.*;
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
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/event")
public class EventController {
    private EventMapper eventMapper;
    private ConnectionMySQLPort connectionMySQLPort;
    private EventMySQLPort eventMySQLPort;
    private BetMySQLPort betMySQLPort;
    private BetMapper betMapper;
    public EventController(
            EventMapper eventMapper,
            ConnectionMySQLPort connectionMySQLPort,
            EventMySQLPort eventMySQLPort,
            BetMapper betMapper,
            BetMySQLPort betMySQLPort
    ){
        this.eventMapper = eventMapper;
        this.connectionMySQLPort = connectionMySQLPort;
        this.eventMySQLPort = eventMySQLPort;
        this.betMapper = betMapper;
        this.betMySQLPort = betMySQLPort;
    }

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BaseResponse<EventResponse>> createEvent(
            @Validated @RequestBody CreateEventRequest request
    ) throws GenericException, IOException {

        CreateEventDto createEventDto = this.eventMapper.convertCreateEventRequestToCreateEventDto(request);
        createEventDto.setFechaHora(LocalDateTime.parse(request.fechaHora));

        CreateEventUseCase createEventUseCase = new CreateEventUseCase(connectionMySQLPort, eventMapper, eventMySQLPort);
        createEventDto = createEventUseCase.run(createEventDto);

        CreateBetUseCase createBetUseCase = new CreateBetUseCase(connectionMySQLPort, betMapper, betMySQLPort);

        CreateBetDto createBetDto1 = new CreateBetDto();
        createBetDto1.setEventId(createEventDto.getId());
        createBetDto1.setName(request.getEquipoA());
        createBetDto1.setPay(request.getPayEquipoA());

        CreateBetDto createBetDto2 = new CreateBetDto();
        createBetDto2.setEventId(createEventDto.getId());
        createBetDto2.setName(request.getEquipoB());
        createBetDto2.setPay(request.getPayEquipoB());

        CreateBetDto createBetDto3 = new CreateBetDto();
        createBetDto3.setEventId(createEventDto.getId());
        createBetDto3.setName("Empate");
        createBetDto3.setPay(request.getPayEmpate());

        CreateBetDto createBetDtoNew1 = createBetUseCase.run(createBetDto1);
        CreateBetDto createBetDtoNew2 = createBetUseCase.run(createBetDto2);
        CreateBetDto createBetDtoNew3 = createBetUseCase.run(createBetDto3);

        EventResponse eventResponse = this.eventMapper.convertCreateEventDtoToEventResponse(createEventDto);

        BaseResponse<EventResponse> response = new BaseResponse();
        response.setMessage("Se ha creado exitosamente el evento");
        response.setContent(eventResponse);
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

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BaseResponse<EventResponse>> updateEvent(
            @PathVariable Integer id,
            @Validated @RequestBody UpdateEventRequest request
    ) throws GenericException, IOException {

        EventDto eventDto = this.eventMapper.convertUpdateEventRequestToEventDto(request);
        eventDto.setFechaHora(LocalDateTime.parse(request.fechaHora));

        UpdateEventUseCase updateEventUseCase = new UpdateEventUseCase(connectionMySQLPort, eventMapper, eventMySQLPort);
        EventDto eventDtoNew = null;//updateEventUseCase.run(eventDto, id);

        BetDto betDto1 = new BetDto();
        betDto1.setId(request.getBetIdEquipoA());
        betDto1.setEventId(eventDtoNew.getId());
        betDto1.setName(request.getEquipoA());
        betDto1.setPay(request.getPayEquipoA());

        BetDto betDto2 = new BetDto();
        betDto2.setId(request.getBetIdEquipoB());
        betDto2.setEventId(eventDtoNew.getId());
        betDto2.setName(request.getEquipoB());
        betDto2.setPay(request.getPayEquipoB());

        BetDto betDto3 = new BetDto();
        betDto3.setId(request.getBetIdEmpate());
        betDto3.setEventId(eventDtoNew.getId());
        betDto3.setName("Empate");
        betDto3.setPay(request.getPayEmpate());

        UpdateBetUseCase updateBetUseCase = new UpdateBetUseCase(connectionMySQLPort, betMapper, betMySQLPort);

        BetDto betDtoNew1 = updateBetUseCase.updateBet(betDto1);
        BetDto betDtoNew2 = updateBetUseCase.updateBet(betDto2);
        BetDto betDtoNew3 = updateBetUseCase.updateBet(betDto3);

        EventResponse eventResponse = this.eventMapper.convertEventDtoToEventResponse(eventDtoNew);

        BaseResponse<EventResponse> response = new BaseResponse();
        response.setMessage("Se ha actualizado exitosamente el evento");
        response.setContent(eventResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PutMapping(path = "/disable/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BaseResponse<EventResponse>> updateStateEvent(
            @PathVariable Integer id,
            @Validated @RequestBody Integer state
    ) throws GenericException, IOException {

        EventDto eventDto = new EventDto();
        eventDto.setId(id);
        eventDto.setStatus(state);

        UpdateEventUseCase updateEventUseCase = new UpdateEventUseCase(connectionMySQLPort, eventMapper, eventMySQLPort);
        EventDto eventDtoNew = updateEventUseCase.run(state, id);

        UpdateBetUseCase updateBetUseCase = new UpdateBetUseCase(connectionMySQLPort, betMapper, betMySQLPort);

        for (BetDto bet: eventDtoNew.getBets()) {
            BetDto betDto = bet;
            betDto.setUpdatedOn(LocalDateTime.now());
            betDto.setStatus(state);
            updateBetUseCase.run(betDto);
        }

        EventResponse eventResponse = this.eventMapper.convertEventDtoToEventResponse(eventDtoNew);

        BaseResponse<EventResponse> response = new BaseResponse();
        response.setMessage("Se ha actualizado el estado exitosamente de la apuesta");
        response.setContent(eventResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


}
