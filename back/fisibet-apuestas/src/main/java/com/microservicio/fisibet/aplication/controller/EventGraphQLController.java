package com.microservicio.fisibet.aplication.controller;


import com.microservicio.fisibet.aplication.dto.*;
import com.microservicio.fisibet.aplication.mapper.BetMapper;
import com.microservicio.fisibet.aplication.mapper.EventMapper;
import com.microservicio.fisibet.aplication.request.CreateEventRequest;
import com.microservicio.fisibet.aplication.request.UpdateEventRequest;
import com.microservicio.fisibet.aplication.response.*;
import com.microservicio.fisibet.aplication.usecase.*;
import com.microservicio.fisibet.domain.exception.GenericException;
import com.microservicio.fisibet.infraestructure.model.BetModel;
import com.microservicio.fisibet.infraestructure.model.EventModel;
import com.microservicio.fisibet.infraestructure.port.BetMySQLPort;
import com.microservicio.fisibet.infraestructure.port.ConnectionMySQLPort;
import com.microservicio.fisibet.infraestructure.port.EventMySQLPort;
import com.microservicio.fisibet.infraestructure.port.spring.BetSpringPort;
import com.microservicio.fisibet.infraestructure.port.spring.EventSpringPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class EventGraphQLController {

    @Autowired
    private EventSpringPort eventSpringPort;
    @Autowired
    private BetSpringPort betSpringPort;
    @Autowired
    private ConnectionMySQLPort connectionMySQLPort;
    @Autowired
    private EventMySQLPort eventMySQLPort;
    @Autowired
    private EventMapper eventMapper;
    @Autowired
    private BetMapper betMapper;
    @Autowired
    private BetMySQLPort betMySQLPort;

    @QueryMapping
    public GraphQLResponse<List<EventModel>> listarEventos() throws GenericException {
        GetEventsUseCase getEventsUseCase = new GetEventsUseCase(connectionMySQLPort, eventMySQLPort, eventMapper);
        List<GetEventDto> getEventDtos = getEventsUseCase.run();

        List<GetEventResponse> getEventResponses = this.eventMapper.convertGetEventDtoToGetEventResponse(getEventDtos);

        GraphQLResponse graphQLResponse = new GraphQLResponse();
        graphQLResponse.setEventType("Lista de eventos");
        graphQLResponse.setContent(getEventResponses);
        return graphQLResponse;
    }

    @QueryMapping
    public GraphQLResponse<GetEventResponse> obtenerEventoPorId(@Argument Integer id) throws GenericException {
        GetEventUseCase getEventUseCase = new GetEventUseCase(connectionMySQLPort, eventMySQLPort, eventMapper);
        GetEventDto getEventDto = getEventUseCase.run(id);

        BetsByEventIdUseCase betsByEventIdUseCase = new BetsByEventIdUseCase(connectionMySQLPort, betMySQLPort, betMapper);
        List<BetDto> betDtos = betsByEventIdUseCase.run(id);
        getEventDto.setBets(betDtos);

        GetEventResponse getEventResponses = this.eventMapper.convertGetEventDtoToGetEventResponse(getEventDto);

        GraphQLResponse graphQLResponse = new GraphQLResponse();
        graphQLResponse.setEventType("Datos del Evento");
        graphQLResponse.setContent(getEventResponses);
        return graphQLResponse;
    }

    @MutationMapping
    public GraphQLResponse<EventResponse> guardarEvento(@Argument CreateEventRequest createEventRequest) throws GenericException {

        CreateEventDto createEventDto = this.eventMapper.convertCreateEventRequestToCreateEventDto(createEventRequest);
        createEventDto.setFechaHora(LocalDateTime.parse(createEventRequest.fechaHora));

        CreateEventUseCase createEventUseCase = new CreateEventUseCase(connectionMySQLPort, eventMapper, eventMySQLPort);
        createEventDto = createEventUseCase.run(createEventDto);

            CreateBetUseCase createBetUseCase = new CreateBetUseCase(connectionMySQLPort, betMapper, betMySQLPort);

        CreateBetDto createBetDto1 = new CreateBetDto();
        createBetDto1.setEventId(createEventDto.getId());
        createBetDto1.setName(createEventRequest.getEquipoA());
        createBetDto1.setPay(createEventRequest.getPayEquipoA());

        CreateBetDto createBetDto2 = new CreateBetDto();
        createBetDto2.setEventId(createEventDto.getId());
        createBetDto2.setName(createEventRequest.getEquipoB());
        createBetDto2.setPay(createEventRequest.getPayEquipoB());

        CreateBetDto createBetDtoNew1 = createBetUseCase.run(createBetDto1);
        CreateBetDto createBetDtoNew2 = createBetUseCase.run(createBetDto2);

        EventResponse eventResponse = this.eventMapper.convertCreateEventDtoToEventResponse(createEventDto);
        GraphQLResponse<EventResponse> graphQLResponse = new GraphQLResponse();
        graphQLResponse.setEventType("Se ha creado exitosamente el evento");
        graphQLResponse.setContent(eventResponse);

        return graphQLResponse;
    }

    @MutationMapping
    public GraphQLResponse<EventResponse> actualizarEvento(@Argument Integer id, @Argument UpdateEventRequest updateEventRequest) throws GenericException {

        EventDto eventDto = this.eventMapper.convertUpdateEventRequestToEventDto(updateEventRequest);
        eventDto.setFechaHora(LocalDateTime.parse(updateEventRequest.fechaHora));

        UpdateEventUseCase updateEventUseCase = new UpdateEventUseCase(connectionMySQLPort, eventMapper, eventMySQLPort);
        EventDto eventDtoNew = updateEventUseCase.run(eventDto, id);

        BetDto betDto1 = new BetDto();
        betDto1.setId(updateEventRequest.getBetIdEquipoA());
        betDto1.setEventId(eventDtoNew.getId());
        betDto1.setName(updateEventRequest.getEquipoA());
        betDto1.setPay(updateEventRequest.getPayEquipoA());

        BetDto betDto2 = new BetDto();
        betDto2.setId(updateEventRequest.getBetIdEquipoB());
        betDto2.setEventId(eventDtoNew.getId());
        betDto2.setName(updateEventRequest.getEquipoB());
        betDto2.setPay(updateEventRequest.getPayEquipoB());

        UpdateBetUseCase updateBetUseCase = new UpdateBetUseCase(connectionMySQLPort, betMapper, betMySQLPort);

        BetDto betDtoNew1 = updateBetUseCase.updateBet(betDto1);
        BetDto betDtoNew2 = updateBetUseCase.updateBet(betDto2);

        GraphQLResponse graphQLResponse = new GraphQLResponse();
        graphQLResponse.setEventType("El evento ha sido actualizado");
        graphQLResponse.setContent(eventDtoNew);
        return graphQLResponse;
    }

    @MutationMapping
    public EventModel eliminarEvento(@Argument Integer id){
        EventModel eventModel = eventSpringPort.findById(id).orElse(null);
        eventModel.setId(id.intValue());
        eventModel.setStatus(2);
        eventModel.setUpdatedOn(LocalDateTime.now());
        List<BetModel> betModel = betSpringPort.getBetByEventId(id);
        if(betModel.size() >= 1){
            for (BetModel bet:
                    betModel ) {
                bet.setStatus(2);
                betSpringPort.save(bet);
            }
        }
       return eventSpringPort.save(eventModel);
    }

}
