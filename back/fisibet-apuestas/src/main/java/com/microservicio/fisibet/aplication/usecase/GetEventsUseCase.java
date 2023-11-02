package com.microservicio.fisibet.aplication.usecase;

import com.microservicio.fisibet.aplication.dto.GetEventDto;
import com.microservicio.fisibet.aplication.mapper.EventMapper;
import com.microservicio.fisibet.aplication.port.ConnectionPort;
import com.microservicio.fisibet.aplication.port.EventPort;
import com.microservicio.fisibet.domain.entity.EventEntity;
import com.microservicio.fisibet.domain.exception.ErrorLevel;
import com.microservicio.fisibet.domain.exception.ErrorStatus;
import com.microservicio.fisibet.domain.exception.GenericException;

import java.util.List;

public class GetEventsUseCase {
    private final ConnectionPort connectionPort;
    private final EventPort eventPort;
    private final EventMapper eventMapper;

    public GetEventsUseCase(ConnectionPort connectionPort,
                            EventPort eventPort,
                            EventMapper eventMapper){
        this.connectionPort = connectionPort;
        this.eventPort = eventPort;
        this.eventMapper = eventMapper;
    }

    public List<GetEventDto> run() throws GenericException {
        try{
            this.connectionPort.begin();
            List<GetEventDto> getEventDtos = getEventDtos();
            this.connectionPort.commit();{

            }
            return getEventDtos;
        }catch (Exception ex){
            this.connectionPort.rollback();
            throw new GenericException(
                    2,
                    ErrorStatus.UNAUTHORIZED,
                    ErrorLevel.ERROR,
                    ex.getMessage(),
                    "Ocurrió un error al obtener la lista de eventos. Por favor, comunícate con el administrador"
            );
        } finally {
            this.connectionPort.close();
        }
    }

    private List<GetEventDto> getEventDtos(){
        List<EventEntity> eventEntities = this.eventPort.GetEventsEnabled();
        return this.eventMapper.convertEventEntitiesToGetEventDto(eventEntities);
    }
}
