package com.microservicio.fisibet.aplication.usecase;

import com.microservicio.fisibet.aplication.dto.CreateBetDto;
import com.microservicio.fisibet.aplication.mapper.BetMapper;
import com.microservicio.fisibet.aplication.port.BetPort;
import com.microservicio.fisibet.aplication.port.ConnectionPort;
import com.microservicio.fisibet.domain.entity.BetEntity;
import com.microservicio.fisibet.domain.exception.ErrorLevel;
import com.microservicio.fisibet.domain.exception.ErrorStatus;
import com.microservicio.fisibet.domain.exception.GenericException;

public class CreateBetUseCase {
    private final ConnectionPort connectionPort;
    private final BetMapper betMapper;
    private final BetPort betPort;

    public CreateBetUseCase(ConnectionPort connectionPort,
                            BetMapper betMapper,
                            BetPort betPort){
        this.connectionPort = connectionPort;
        this.betMapper = betMapper;
        this.betPort = betPort;
    }

    public CreateBetDto run(CreateBetDto request) throws GenericException {
        try{
            this.connectionPort.begin();
            Integer betLastId = createBet(request);
            CreateBetDto createBetDto = new CreateBetDto();
            createBetDto.setId(betLastId);
            this.connectionPort.commit();{
            }
            return createBetDto;
        }catch (Exception ex){
            this.connectionPort.rollback();
            throw new GenericException(
                    2,
                    ErrorStatus.UNAUTHORIZED,
                    ErrorLevel.ERROR,
                    ex.getMessage(),
                    "Ocurrió un error al crear la apuesta. Por favor, comunícate con el administrador"
            );
        } finally {
            this.connectionPort.close();
        }
    }

    public Integer createBet(CreateBetDto request){
        BetEntity betEntity = this.betMapper.convertCreateBetDtoToBetEntity(request);
        return this.betPort.registerBet(betEntity);
    }
}
