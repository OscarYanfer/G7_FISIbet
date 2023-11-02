package com.microservicio.fisibet.aplication.usecase;

import com.microservicio.fisibet.aplication.dto.CreateTicketBetDto;
import com.microservicio.fisibet.aplication.mapper.TicketBetMapper;
import com.microservicio.fisibet.aplication.port.ConnectionPort;
import com.microservicio.fisibet.aplication.port.TicketBetPort;
import com.microservicio.fisibet.domain.entity.TicketBetEntity;
import com.microservicio.fisibet.domain.exception.ErrorLevel;
import com.microservicio.fisibet.domain.exception.ErrorStatus;
import com.microservicio.fisibet.domain.exception.GenericException;

public class CreateTicketBetUseCase {
    private final ConnectionPort connectionPort;
    private final TicketBetMapper ticketBetMapper;
    private final TicketBetPort ticketBetPort;

    public CreateTicketBetUseCase(
            ConnectionPort connectionPort,
            TicketBetMapper ticketBetMapper,
            TicketBetPort ticketBetPort
    ){
        this.connectionPort = connectionPort;
        this.ticketBetMapper = ticketBetMapper;
        this.ticketBetPort = ticketBetPort;
    }

    public Integer run(CreateTicketBetDto request) throws GenericException {
        try{
            this.connectionPort.begin();
            Integer ticketBetId = createTicketBet(request);
            this.connectionPort.commit();{
            }
            return ticketBetId;
        }catch (Exception ex){
            this.connectionPort.rollback();
            throw new GenericException(
                    2,
                    ErrorStatus.UNAUTHORIZED,
                    ErrorLevel.ERROR,
                    ex.getMessage(),
                    "Ocurrió un error al crear el ticketbet. Por favor, comunícate con el administrador"
            );
        } finally {
            this.connectionPort.close();
        }
    }

    public Integer createTicketBet(CreateTicketBetDto createTicketBetDto){
        TicketBetEntity ticketBetEntity = this.ticketBetMapper.convertCreateTicketBetDtoToTicketBetEntity(createTicketBetDto);
        return this.ticketBetPort.createTicketBet(ticketBetEntity);
    }
}
