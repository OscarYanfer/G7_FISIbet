package com.microservicio.fisibet.aplication.usecase;

import com.microservicio.fisibet.aplication.dto.GetEventDto;
import com.microservicio.fisibet.aplication.dto.TicketDto;
import com.microservicio.fisibet.aplication.mapper.TicketMapper;
import com.microservicio.fisibet.aplication.port.ConnectionPort;
import com.microservicio.fisibet.aplication.port.TicketPort;
import com.microservicio.fisibet.domain.entity.TicketEntity;
import com.microservicio.fisibet.domain.exception.ErrorLevel;
import com.microservicio.fisibet.domain.exception.ErrorStatus;
import com.microservicio.fisibet.domain.exception.GenericException;

public class GetTicketUseCase {
    public TicketPort ticketPort;
    public TicketMapper ticketMapper;
    public ConnectionPort connectionPort;

    public GetTicketUseCase(ConnectionPort connectionPort,
                            TicketPort ticketPort,
                            TicketMapper ticketMapper){
        this.connectionPort = connectionPort;
        this.ticketPort = ticketPort;
        this.ticketMapper = ticketMapper;
    }

    public TicketDto run(Integer id) throws GenericException {
        try{
            this.connectionPort.begin();
            TicketDto ticketDto = getTicketDto(id);
            this.connectionPort.commit();{
            }
            return ticketDto;
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

    private TicketDto getTicketDto(Integer id){
        TicketEntity ticketEntity = this.ticketPort.getTicketById(id);
        return this.ticketMapper.convertTicketEntityToTicketDto(ticketEntity);
    }
}
