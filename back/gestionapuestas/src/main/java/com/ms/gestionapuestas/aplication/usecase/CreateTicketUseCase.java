package com.ms.gestionapuestas.aplication.usecase;

import com.ms.gestionapuestas.aplication.dto.CreateEventDto;
import com.ms.gestionapuestas.aplication.dto.CreateTicketDto;
import com.ms.gestionapuestas.aplication.mapper.TicketMapper;
import com.ms.gestionapuestas.aplication.port.ConnectionPort;
import com.ms.gestionapuestas.aplication.port.TicketPort;
import com.ms.gestionapuestas.domain.entity.TicketEntity;
import com.ms.gestionapuestas.domain.exception.ErrorLevel;
import com.ms.gestionapuestas.domain.exception.ErrorStatus;
import com.ms.gestionapuestas.domain.exception.GenericException;

public class CreateTicketUseCase {
    private final TicketMapper ticketMapper;
    private final ConnectionPort connectionPort;
    private final TicketPort ticketPort;
    public CreateTicketUseCase(
            ConnectionPort connectionPort,
            TicketMapper ticketMapper,
            TicketPort ticketPort
    ){
        this.ticketMapper = ticketMapper;
        this.connectionPort = connectionPort;
        this.ticketPort = ticketPort;
    }

    public CreateTicketDto run(CreateTicketDto request) throws GenericException {
        try{
            this.connectionPort.begin();
            CreateTicketDto createTicketDto = createTicket(request);
            this.connectionPort.commit();{
            }
            return createTicketDto;
        }catch (Exception ex){
            this.connectionPort.rollback();
            throw new GenericException(
                    2,
                    ErrorStatus.UNAUTHORIZED,
                    ErrorLevel.ERROR,
                    ex.getMessage(),
                    "Ocurrió un error al crear el evento. Por favor, comunícate con el administrador"
            );
        } finally {
            this.connectionPort.close();
        }
    }

    private CreateTicketDto createTicket(CreateTicketDto createTicketDto){
        TicketEntity ticketEntity = this.ticketMapper.convertCreateTicketDtoToTicketEntity(createTicketDto);
        CreateTicketDto createTicketDtoNew = this.ticketMapper.convertTicketEntityToCreateTicketDto(this.ticketPort.createTicket(ticketEntity));
        return createTicketDtoNew;
    }
}
