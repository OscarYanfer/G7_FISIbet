package com.microservicio.fisibet.aplication.usecase;

import com.microservicio.fisibet.aplication.dto.AccountUserDto;
import com.microservicio.fisibet.aplication.mapper.AccountUserMapper;
import com.microservicio.fisibet.aplication.port.AccountUserPort;
import com.microservicio.fisibet.aplication.port.ConnectionPort;
import com.microservicio.fisibet.domain.exception.ErrorLevel;
import com.microservicio.fisibet.domain.exception.ErrorStatus;
import com.microservicio.fisibet.domain.exception.GenericException;

import java.util.List;

public class GetAccountUsersUseCase {
    private AccountUserPort accountUserPort;
    private AccountUserMapper accountUserMapper;
    private ConnectionPort connectionPort;
    public GetAccountUsersUseCase(AccountUserMapper accountUserMapper,
                                     AccountUserPort accountUserPort,
                                     ConnectionPort connectionPort){
        this.accountUserMapper = accountUserMapper;
        this.accountUserPort = accountUserPort;
        this.connectionPort = connectionPort;
    }

    public List<AccountUserDto> run() throws GenericException {
        try{
            this.connectionPort.begin();
            List<AccountUserDto> accountUserDtos = getAccountUsers();

            this.connectionPort.commit();
            return accountUserDtos;
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

    public List<AccountUserDto> getAccountUsers(){
        return this.accountUserMapper.convertAccountUserEntitiesToAccountUserDtos(this.accountUserPort.getAccountUsers());
    };
}
