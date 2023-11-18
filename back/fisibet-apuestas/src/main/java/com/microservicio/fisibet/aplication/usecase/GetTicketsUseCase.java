package com.microservicio.fisibet.aplication.usecase;

import com.microservicio.fisibet.aplication.dto.TicketDto;
import com.microservicio.fisibet.aplication.mapper.TicketMapper;
import com.microservicio.fisibet.aplication.port.ConnectionPort;
import com.microservicio.fisibet.aplication.port.TicketPort;
import com.microservicio.fisibet.domain.entity.TicketEntity;
import com.microservicio.fisibet.domain.exception.ErrorLevel;
import com.microservicio.fisibet.domain.exception.ErrorStatus;
import com.microservicio.fisibet.domain.exception.GenericException;

import java.util.List;

public class GetTicketsUseCase {
    public TicketPort ticketPort;
    public TicketMapper ticketMapper;
    public ConnectionPort connectionPort;

    public GetTicketsUseCase(ConnectionPort connectionPort,
                            TicketPort ticketPort,
                            TicketMapper ticketMapper){
        this.connectionPort = connectionPort;
        this.ticketPort = ticketPort;
        this.ticketMapper = ticketMapper;
    }

    public List<TicketDto> run() throws GenericException {
        try{
            this.connectionPort.begin();
            List<TicketDto> ticketDtos = getTicketDtos();
            this.connectionPort.commit();{
            }
            return ticketDtos;
        }catch (Exception ex){
            this.connectionPort.rollback();
            throw new GenericException(
                    2,
                    ErrorStatus.UNAUTHORIZED,
                    ErrorLevel.ERROR,
                    ex.getMessage(),
                    "Ocurrió un error al obtener los datos del ticket. Por favor, comunícate con el administrador"
            );
        } finally {
            this.connectionPort.close();
        }
    }

    private List<TicketDto> getTicketDtos(){
        List<TicketEntity> ticketEntities = this.ticketPort.getTickets();
        return this.ticketMapper.convertTicketEntitiesToTicketDtos(ticketEntities);
    }
}
