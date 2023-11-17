package com.microservicio.fisibet.aplication.usecase;

import com.microservicio.fisibet.aplication.dto.BetDto;
import com.microservicio.fisibet.aplication.dto.GetEventDto;
import com.microservicio.fisibet.aplication.mapper.BetMapper;
import com.microservicio.fisibet.aplication.port.BetPort;
import com.microservicio.fisibet.aplication.port.ConnectionPort;
import com.microservicio.fisibet.domain.entity.BetEntity;
import com.microservicio.fisibet.domain.exception.ErrorLevel;
import com.microservicio.fisibet.domain.exception.ErrorStatus;
import com.microservicio.fisibet.domain.exception.GenericException;

import java.util.List;

public class BetsByEventIdUseCase {
    private final ConnectionPort connectionPort;
    private final BetPort betPort;
    private final BetMapper betMapper;
    public BetsByEventIdUseCase(ConnectionPort connectionPort,
                          BetPort betPort,
                          BetMapper betMapper){
        this.connectionPort = connectionPort;
        this.betPort = betPort;
        this.betMapper = betMapper;
    }

    public List<BetDto> run(Integer eventId) throws GenericException {
        try{
            this.connectionPort.begin();
            List<BetDto> betDtos = obtenerBetsByEventId(eventId);
            this.connectionPort.commit();{
            }
            return betDtos;
        }catch (Exception ex){
            this.connectionPort.rollback();
            throw new GenericException(
                    2,
                    ErrorStatus.UNAUTHORIZED,
                    ErrorLevel.ERROR,
                    ex.getMessage(),
                    "Ocurrió un error al obtener la lista de apuestas. Por favor, comunícate con el administrador"
            );
        } finally {
            this.connectionPort.close();
        }
    }

    private List<BetDto> obtenerBetsByEventId(Integer eventId){
        List<BetEntity> betEntities = this.betPort.getBetsByEventId(eventId);
        return this.betMapper.convertBetEntitiesToBetDtos(betEntities);
    }
}
