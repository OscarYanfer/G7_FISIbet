package com.ms.gestionapuestas.aplication.usecase;

import com.ms.gestionapuestas.aplication.dto.GetBetDto;
import com.ms.gestionapuestas.aplication.dto.GetEventDto;
import com.ms.gestionapuestas.aplication.mapper.BetMapper;
import com.ms.gestionapuestas.aplication.port.BetPort;
import com.ms.gestionapuestas.aplication.port.ConnectionPort;
import com.ms.gestionapuestas.domain.entity.BetEntity;
import com.ms.gestionapuestas.domain.exception.ErrorLevel;
import com.ms.gestionapuestas.domain.exception.ErrorStatus;
import com.ms.gestionapuestas.domain.exception.GenericException;

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
