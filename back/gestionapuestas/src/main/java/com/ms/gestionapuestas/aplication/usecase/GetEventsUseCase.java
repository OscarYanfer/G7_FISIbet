package com.ms.gestionapuestas.aplication.usecase;

import com.ms.gestionapuestas.aplication.dto.GetEventDto;
import com.ms.gestionapuestas.aplication.mapper.EventMapper;
import com.ms.gestionapuestas.aplication.port.ConnectionPort;
import com.ms.gestionapuestas.aplication.port.EventPort;
import com.ms.gestionapuestas.aplication.response.GetEventResponse;
import com.ms.gestionapuestas.domain.entity.EventEntity;
import com.ms.gestionapuestas.domain.exception.ErrorLevel;
import com.ms.gestionapuestas.domain.exception.ErrorStatus;
import com.ms.gestionapuestas.domain.exception.GenericException;

import java.util.ArrayList;
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
