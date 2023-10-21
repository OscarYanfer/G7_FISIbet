package com.ms.gestionapuestas.aplication.usecase;

import com.ms.gestionapuestas.aplication.dto.CreateBetDto;
import com.ms.gestionapuestas.aplication.dto.CreateEventDto;
import com.ms.gestionapuestas.aplication.mapper.BetMapper;
import com.ms.gestionapuestas.aplication.port.BetPort;
import com.ms.gestionapuestas.aplication.port.ConnectionPort;
import com.ms.gestionapuestas.domain.entity.BetEntity;
import com.ms.gestionapuestas.domain.exception.ErrorLevel;
import com.ms.gestionapuestas.domain.exception.ErrorStatus;
import com.ms.gestionapuestas.domain.exception.GenericException;

import javax.swing.text.html.parser.Entity;

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
