package com.microservicio.fisibet.aplication.usecase;

import com.microservicio.fisibet.aplication.dto.GetEventDto;
import com.microservicio.fisibet.aplication.mapper.EventMapper;
import com.microservicio.fisibet.aplication.port.ConnectionPort;
import com.microservicio.fisibet.aplication.port.EventPort;
import com.microservicio.fisibet.domain.entity.EventEntity;
import com.microservicio.fisibet.domain.exception.ErrorLevel;
import com.microservicio.fisibet.domain.exception.ErrorStatus;
import com.microservicio.fisibet.domain.exception.GenericException;


public class GetEventUseCase {
    private final ConnectionPort connectionPort;
    private final EventPort eventPort;
    private final EventMapper eventMapper;

    public GetEventUseCase(ConnectionPort connectionPort,
                            EventPort eventPort,
                            EventMapper eventMapper){
        this.connectionPort = connectionPort;
        this.eventPort = eventPort;
        this.eventMapper = eventMapper;
    }

    public GetEventDto run(Integer id) throws GenericException {
        try{
            this.connectionPort.begin();
            GetEventDto getEventDto = getEventDto(id);
            this.connectionPort.commit();{

            }
            return getEventDto;
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

    private GetEventDto getEventDto(Integer id){
        EventEntity eventEntity = this.eventPort.getEventById(id);
        return this.eventMapper.convertEventEntityToGetEventDto(eventEntity);
    }
}
