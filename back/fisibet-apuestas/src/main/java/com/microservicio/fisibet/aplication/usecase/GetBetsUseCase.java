package com.microservicio.fisibet.aplication.usecase;

import com.microservicio.fisibet.aplication.dto.GetBetDto;
import com.microservicio.fisibet.aplication.mapper.BetMapper;
import com.microservicio.fisibet.aplication.port.BetPort;
import com.microservicio.fisibet.aplication.port.ConnectionPort;
import com.microservicio.fisibet.domain.entity.BetEntity;
import com.microservicio.fisibet.domain.exception.ErrorLevel;
import com.microservicio.fisibet.domain.exception.ErrorStatus;
import com.microservicio.fisibet.domain.exception.GenericException;

import java.util.List;

public class GetBetsUseCase {
    private final ConnectionPort connectionPort;
    private final BetPort betPort;
    private final BetMapper betMapper;
    public GetBetsUseCase(ConnectionPort connectionPort,
                          BetPort betPort,
                          BetMapper betMapper){
        this.connectionPort = connectionPort;
        this.betPort = betPort;
        this.betMapper = betMapper;
    }

    public List<GetBetDto> run() throws GenericException {
        try{
            this.connectionPort.begin();
            List<GetBetDto> getBetDtos = getBetDtos();
            this.connectionPort.commit();{
            }
            return getBetDtos;
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

    public List<GetBetDto> getBetDtos(){
        List<BetEntity> betEntities = this.betPort.getBets();
        return this.betMapper.convertBetEntitiesToGetBetDtos(betEntities);
    }
}
