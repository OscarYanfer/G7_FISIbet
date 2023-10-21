package com.ms.gestionapuestas.aplication.usecase;

import com.ms.gestionapuestas.aplication.dto.CreateEventDto;
import com.ms.gestionapuestas.aplication.dto.GetEventDto;
import com.ms.gestionapuestas.aplication.mapper.EventMapper;
import com.ms.gestionapuestas.aplication.port.ConnectionPort;
import com.ms.gestionapuestas.aplication.port.EventPort;
import com.ms.gestionapuestas.aplication.request.CreateEventRequest;
import com.ms.gestionapuestas.domain.entity.EventEntity;
import com.ms.gestionapuestas.domain.exception.ErrorLevel;
import com.ms.gestionapuestas.domain.exception.ErrorStatus;
import com.ms.gestionapuestas.domain.exception.GenericException;
import com.ms.gestionapuestas.infraestructure.port.EventMySQLPort;

import java.util.List;

public class CreateEventUseCase {
    private final ConnectionPort connectionPort;
    private final EventMapper eventMapper;
    private final EventPort eventPort;

    public CreateEventUseCase(ConnectionPort connectionPort,
                              EventMapper eventMapper,
                              EventPort eventPort){
        this.connectionPort = connectionPort;
        this.eventMapper = eventMapper;
        this.eventPort = eventPort;
    }

    public CreateEventDto run(CreateEventDto request) throws GenericException {
        try{
            this.connectionPort.begin();
            Integer eventLastId = createEvent(request);
            CreateEventDto createEventDto = new CreateEventDto();
            createEventDto.setId(eventLastId);
            this.connectionPort.commit();{
            }
            return createEventDto;
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

    private Integer createEvent(CreateEventDto request){
        EventEntity eventEntity = this.eventMapper.convertCreateEventDtoToEventEntity(request);
        return this.eventPort.registerEvent(eventEntity);
    }
}
