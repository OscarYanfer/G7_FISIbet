package com.microservicio.fisibet.aplication.usecase;

import com.microservicio.fisibet.aplication.dto.AccountUserDto;
import com.microservicio.fisibet.aplication.dto.AuthDto;
import com.microservicio.fisibet.aplication.mapper.AccountUserMapper;
import com.microservicio.fisibet.aplication.mapper.SessionUserMapper;
import com.microservicio.fisibet.aplication.port.AccountUserPort;
import com.microservicio.fisibet.aplication.port.ConnectionPort;
import com.microservicio.fisibet.aplication.port.SessionUserPort;
import com.microservicio.fisibet.domain.entity.SessionUserEntity;
import com.microservicio.fisibet.domain.exception.ErrorLevel;
import com.microservicio.fisibet.domain.exception.ErrorStatus;
import com.microservicio.fisibet.domain.exception.GenericException;

public class LoginUseCase {

    private SessionUserPort sessionUserPort;
    private SessionUserMapper sessionUserMapper;
    private ConnectionPort connectionPort;
    public LoginUseCase(SessionUserMapper sessionUserMapper,
                                    SessionUserPort sessionUserPort,
                                    ConnectionPort connectionPort){
        this.sessionUserMapper = sessionUserMapper;
        this.sessionUserPort = sessionUserPort;
        this.connectionPort = connectionPort;
    }

    public AuthDto run(AuthDto request) throws GenericException {
        try{
            this.connectionPort.begin();
            AuthDto authDto = login(request);

            this.connectionPort.commit();
            return authDto;
        }catch (Exception ex){
            this.connectionPort.rollback();
            throw new GenericException(
                    2,
                    ErrorStatus.UNAUTHORIZED,
                    ErrorLevel.ERROR,
                    ex.getMessage(),
                    "Ocurrió un error al iniciar sessión. Por favor, comunícate con el administrador"
            );
        } finally {
            this.connectionPort.close();
        }
    }

    private AuthDto login(AuthDto request){
        SessionUserEntity sessionUserEntity = this.sessionUserMapper.convertAuthDtoToSessionUserEntity(request);
        SessionUserEntity sessionUserEntityL1 = this.sessionUserPort.login(sessionUserEntity);
        return this.sessionUserMapper.convertSessionUserEntityToAuthDto(sessionUserEntityL1);
    }
}
