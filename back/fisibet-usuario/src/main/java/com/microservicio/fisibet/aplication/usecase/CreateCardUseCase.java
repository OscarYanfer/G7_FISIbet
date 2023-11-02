package com.microservicio.fisibet.aplication.usecase;

import com.microservicio.fisibet.aplication.dto.CardDto;
import com.microservicio.fisibet.aplication.dto.CreateCardDto;
import com.microservicio.fisibet.aplication.mapper.CardMapper;
import com.microservicio.fisibet.aplication.port.ConnectionPort;
import com.microservicio.fisibet.aplication.port.CardPort;
import com.microservicio.fisibet.domain.entity.CardEntity;
import com.microservicio.fisibet.domain.exception.ErrorLevel;
import com.microservicio.fisibet.domain.exception.ErrorStatus;
import com.microservicio.fisibet.domain.exception.GenericException;

public class CreateCardUseCase {
    private ConnectionPort connectionPort;
    private CardMapper cardMapper;
    private CardPort cardPort;
    public CreateCardUseCase(ConnectionPort connectionPort,
                             CardMapper cardMapper,
                             CardPort cardPort){
        this.connectionPort = connectionPort;
        this.cardMapper = cardMapper;
        this.cardPort = cardPort;
    }

    public CardDto run(CreateCardDto request) throws GenericException {
        try{
            this.connectionPort.begin();
            CardDto cardDto = registerCard(request);

            this.connectionPort.commit();
            return cardDto;
        }catch (Exception ex){
            this.connectionPort.rollback();
            throw new GenericException(
                    2,
                    ErrorStatus.UNAUTHORIZED,
                    ErrorLevel.ERROR,
                    ex.getMessage(),
                    "Ocurrió un error al registrar la billetera. Por favor, comunícate con el administrador"
            );
        } finally {
            this.connectionPort.close();
        }
    }

    public CardDto registerCard(CreateCardDto createCardDto){
        CardEntity cardEntity = this.cardMapper.convertCreateCardDtoToCardEntity(createCardDto);
        CardEntity cardEntity1 = this.cardPort.registerWallet(cardEntity);
        return this.cardMapper.convertCardEntityToCardDto(cardEntity1);
    }


}
