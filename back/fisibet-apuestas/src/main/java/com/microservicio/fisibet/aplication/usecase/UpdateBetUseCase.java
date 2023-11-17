package com.microservicio.fisibet.aplication.usecase;

import com.microservicio.fisibet.aplication.dto.BetDto;
import com.microservicio.fisibet.aplication.dto.CreateBetDto;
import com.microservicio.fisibet.aplication.mapper.BetMapper;
import com.microservicio.fisibet.aplication.port.BetPort;
import com.microservicio.fisibet.aplication.port.ConnectionPort;
import com.microservicio.fisibet.domain.entity.BetEntity;
import com.microservicio.fisibet.domain.exception.ErrorLevel;
import com.microservicio.fisibet.domain.exception.ErrorStatus;
import com.microservicio.fisibet.domain.exception.GenericException;

public class UpdateBetUseCase {
    private final ConnectionPort connectionPort;
    private final BetMapper betMapper;
    private final BetPort betPort;

    public UpdateBetUseCase(ConnectionPort connectionPort, BetMapper betMapper,
                            BetPort betPort){
        this.connectionPort = connectionPort;
        this.betMapper = betMapper;
        this.betPort = betPort;
    }

    public BetDto run(BetDto request) throws GenericException {
        try{
            this.connectionPort.begin();
            BetDto betDto = updateBet(request);
            this.connectionPort.commit();{
            }
            return betDto;
        }catch (Exception ex){
            this.connectionPort.rollback();
            throw new GenericException(
                    2,
                    ErrorStatus.UNAUTHORIZED,
                    ErrorLevel.ERROR,
                    ex.getMessage(),
                    "Ocurrió un error al actualizar la apuesta. Por favor, comunícate con el administrador"
            );
        } finally {
            this.connectionPort.close();
        }
    }

    public BetDto updateBet(BetDto request){
        BetEntity betEntity = this.betMapper.convertBetDtoToBetEntity(request);
        return this.betMapper.convetBetEntityToBetDto(this.betPort.updateBetByEvent(betEntity));
    }
}
