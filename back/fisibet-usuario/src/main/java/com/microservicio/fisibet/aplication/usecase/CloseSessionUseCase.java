package com.microservicio.fisibet.aplication.usecase;

import com.microservicio.fisibet.aplication.dto.AccountUserDto;
import com.microservicio.fisibet.aplication.dto.SessionUserDto;
import com.microservicio.fisibet.aplication.mapper.SessionUserMapper;
import com.microservicio.fisibet.aplication.port.ConnectionPort;
import com.microservicio.fisibet.aplication.port.SessionUserPort;
import com.microservicio.fisibet.domain.entity.SessionUserEntity;
import com.microservicio.fisibet.domain.exception.ErrorLevel;
import com.microservicio.fisibet.domain.exception.ErrorStatus;
import com.microservicio.fisibet.domain.exception.GenericException;

public class CloseSessionUseCase {

    private SessionUserPort sessionUserPort;

    private SessionUserMapper mapper;

    private ConnectionPort connectionPort;

    public CloseSessionUseCase(SessionUserPort sessionUserPort, SessionUserMapper mapper, ConnectionPort connectionPort) {
        this.sessionUserPort = sessionUserPort;
        this.mapper = mapper;
        this.connectionPort = connectionPort;
    }

    public SessionUserDto run(String username) throws GenericException {
        try{
            this.connectionPort.begin();
            SessionUserDto sessionUserDto = closeSession(username);

            this.connectionPort.commit();
            return sessionUserDto;
        }catch (Exception ex){
            this.connectionPort.rollback();
            throw new GenericException(
                    2,
                    ErrorStatus.UNAUTHORIZED,
                    ErrorLevel.ERROR,
                    ex.getMessage(),
                    "Ocurrió un error al cerrar sesion. Por favor, comunícate con el administrador"
            );
        } finally {
            this.connectionPort.close();
        }
    }

    private SessionUserDto closeSession(String username) {
        SessionUserEntity entity = this.sessionUserPort.closeSession(username);
        return this.mapper.convertSessionUserEntityToSessionUserDto(entity);
    }

}
