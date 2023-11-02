package com.microservicio.fisibet.aplication.usecase;

import com.microservicio.fisibet.aplication.dto.CreateEventDto;
import com.microservicio.fisibet.aplication.mapper.EventMapper;
import com.microservicio.fisibet.aplication.port.ConnectionPort;
import com.microservicio.fisibet.aplication.port.EventPort;
import com.microservicio.fisibet.domain.entity.EventEntity;
import com.microservicio.fisibet.domain.exception.ErrorLevel;
import com.microservicio.fisibet.domain.exception.ErrorStatus;
import com.microservicio.fisibet.domain.exception.GenericException;

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
