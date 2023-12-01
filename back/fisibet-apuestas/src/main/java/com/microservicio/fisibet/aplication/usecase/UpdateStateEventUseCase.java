package com.microservicio.fisibet.aplication.usecase;

import com.microservicio.fisibet.aplication.dto.EventDto;
import com.microservicio.fisibet.aplication.mapper.EventMapper;
import com.microservicio.fisibet.aplication.port.ConnectionPort;
import com.microservicio.fisibet.aplication.port.EventPort;
import com.microservicio.fisibet.domain.exception.ErrorLevel;
import com.microservicio.fisibet.domain.exception.ErrorStatus;
import com.microservicio.fisibet.domain.exception.GenericException;


public class UpdateStateEventUseCase {
    private final ConnectionPort connectionPort;
    private final EventMapper eventMapper;
    private final EventPort eventPort;

    public UpdateStateEventUseCase(ConnectionPort connectionPort, EventMapper eventMapper,
                                   EventPort eventPort){
        this.connectionPort = connectionPort;
        this.eventMapper = eventMapper;
        this.eventPort = eventPort;
    }

    public EventDto run(Integer state, Integer eventId) throws GenericException {
        try{
            this.connectionPort.begin();
            EventDto eventDto = updateEvent(state, eventId);
            this.connectionPort.commit();{
            }
            return eventDto;
        }catch (Exception ex){
            this.connectionPort.rollback();
            throw new GenericException(
                    2,
                    ErrorStatus.UNAUTHORIZED,
                    ErrorLevel.ERROR,
                    ex.getMessage(),
                    "Ocurrió un error al actualizar el evento. Por favor, comunícate con el administrador"
            );
        } finally {
            this.connectionPort.close();
        }
    }

    private EventDto updateEvent(Integer state, Integer eventId){
        return this.eventMapper.convertEventDtoToEventEntity(this.eventPort.updateStateEventById(eventId,state));
    }

}
