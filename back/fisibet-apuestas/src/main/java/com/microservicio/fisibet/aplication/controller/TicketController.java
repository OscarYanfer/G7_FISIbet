package com.microservicio.fisibet.aplication.controller;

import com.microservicio.fisibet.aplication.dto.CreateTicketBetDto;
import com.microservicio.fisibet.aplication.dto.CreateTicketDto;
import com.microservicio.fisibet.aplication.dto.GetEventDto;
import com.microservicio.fisibet.aplication.dto.TicketDto;
import com.microservicio.fisibet.aplication.mapper.TicketBetMapper;
import com.microservicio.fisibet.aplication.mapper.TicketMapper;
import com.microservicio.fisibet.aplication.request.CreateTicketRequest;
import com.microservicio.fisibet.aplication.response.BaseResponse;
import com.microservicio.fisibet.aplication.response.CreateTicketResponse;
import com.microservicio.fisibet.aplication.response.GetEventResponse;
import com.microservicio.fisibet.aplication.response.TicketResponse;
import com.microservicio.fisibet.aplication.usecase.*;
import com.microservicio.fisibet.domain.exception.GenericException;
import com.microservicio.fisibet.infraestructure.port.ConnectionMySQLPort;
import com.microservicio.fisibet.infraestructure.port.TicketBetMySQLPort;
import com.microservicio.fisibet.infraestructure.port.TicketMySQLPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/ticket")
public class TicketController {
    private TicketMapper ticketMapper;
    private ConnectionMySQLPort connectionMySQLPort;
    private TicketMySQLPort ticketMySQLPort;
    private TicketBetMapper ticketBetMapper;
    private TicketBetMySQLPort ticketBetMySQLPort;
    public TicketController(TicketMapper ticketMapper, ConnectionMySQLPort connectionMySQLPort,
                            TicketMySQLPort ticketMySQLPort, TicketBetMapper ticketBetMapper,
                            TicketBetMySQLPort ticketBetMySQLPort){
        this.ticketMapper = ticketMapper;
        this.connectionMySQLPort = connectionMySQLPort;
        this.ticketMySQLPort = ticketMySQLPort;
        this.ticketBetMapper = ticketBetMapper;
        this.ticketBetMySQLPort = ticketBetMySQLPort;
    }

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BaseResponse<CreateTicketResponse>> createTicket(
            @Validated @RequestBody CreateTicketRequest request
    ) throws GenericException, IOException {

        CreateTicketDto createTicketDto = this.ticketMapper.convertCreateTicketRequestToCreateTicketDto(request);
        CreateTicketUseCase createTicketUseCase = new CreateTicketUseCase(connectionMySQLPort, ticketMapper, ticketMySQLPort);
        createTicketDto = createTicketUseCase.run(createTicketDto);

        for (Integer id: request.getBetIds()) {
            CreateTicketBetDto createTicketBetDto = new CreateTicketBetDto();
            createTicketBetDto.setIdTicket(createTicketDto.getId());
            createTicketBetDto.setIdBet(id);

            CreateTicketBetUseCase createTicketBetUseCase = new CreateTicketBetUseCase(connectionMySQLPort, ticketBetMapper, ticketBetMySQLPort);
            Integer ticketBetId = createTicketBetUseCase.run(createTicketBetDto);
        }

        CreateTicketResponse createTicketResponse = this.ticketMapper.convertCreateTicketDtoToCreateTicketResponse(createTicketDto);
        BaseResponse<CreateTicketResponse> response = new BaseResponse();
        response.setMessage("Se ha creado exitosamente el ticket");
        response.setContent(createTicketResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<BaseResponse<TicketResponse>> getTicketById(
            @PathVariable Integer id
    ) throws GenericException {
        GetTicketUseCase getTicketUseCase = new GetTicketUseCase(connectionMySQLPort, ticketMySQLPort, ticketMapper);
        TicketDto ticketDto = getTicketUseCase.run(id);

        TicketResponse ticketResponse = this.ticketMapper.convertTicketDtoToTicketResponse(ticketDto);

        BaseResponse response = new BaseResponse();
        response.setMessage("Datos del ticket");
        response.setContent(ticketResponse);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/all")
    public @ResponseBody ResponseEntity<BaseResponse<List<TicketResponse>>> getTickets(
    ) throws GenericException {

        GetTicketsUseCase getTicketUseCase = new GetTicketsUseCase(connectionMySQLPort, ticketMySQLPort, ticketMapper);
        List<TicketDto> ticketDtos = getTicketUseCase.run();

        List<TicketResponse> ticketResponse = this.ticketMapper.convertTicketDtosToTicketResponses(ticketDtos);

        BaseResponse response = new BaseResponse();
        response.setMessage("Lista de tickets");
        response.setContent(ticketResponse);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
