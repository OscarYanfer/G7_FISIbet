package com.ms.gestionapuestas.aplication.usecase;

import com.ms.gestionapuestas.aplication.dto.CreateEventDto;
import com.ms.gestionapuestas.aplication.dto.CreateTicketBetDto;
import com.ms.gestionapuestas.aplication.mapper.TicketBetMapper;
import com.ms.gestionapuestas.aplication.port.ConnectionPort;
import com.ms.gestionapuestas.aplication.port.TicketBetPort;
import com.ms.gestionapuestas.domain.entity.TicketBetEntity;
import com.ms.gestionapuestas.domain.exception.ErrorLevel;
import com.ms.gestionapuestas.domain.exception.ErrorStatus;
import com.ms.gestionapuestas.domain.exception.GenericException;

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
