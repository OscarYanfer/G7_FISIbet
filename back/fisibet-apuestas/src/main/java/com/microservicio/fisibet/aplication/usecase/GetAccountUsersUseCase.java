package com.microservicio.fisibet.aplication.usecase;

import com.microservicio.fisibet.aplication.dto.AccountUserDto;
import com.microservicio.fisibet.aplication.mapper.AccountUserMapper;
import com.microservicio.fisibet.aplication.port.AccountUserPort;
import com.microservicio.fisibet.aplication.port.ConnectionPort;
import com.microservicio.fisibet.domain.entity.AccountUserEntity;
import com.microservicio.fisibet.domain.exception.ErrorLevel;
import com.microservicio.fisibet.domain.exception.ErrorStatus;
import com.microservicio.fisibet.domain.exception.GenericException;

import java.util.List;

public class GetAccountUsersUseCase {
    private AccountUserMapper accountUserMapper;
    private ConnectionPort connectionPort;
    private AccountUserPort accountUserPort;

    public GetAccountUsersUseCase(AccountUserMapper accountUserMapper,
                                  ConnectionPort connectionPort,
                                  AccountUserPort accountUserPort){
        this.accountUserMapper = accountUserMapper;
        this.connectionPort = connectionPort;
        this.accountUserPort = accountUserPort;
    }

    public List<AccountUserDto> run() throws GenericException {
        try{
            this.connectionPort.begin();
            List<AccountUserDto> accountUserDtos = getAccountUsers();
            this.connectionPort.commit();{
            }
            return accountUserDtos;
        }catch (Exception ex){
            this.connectionPort.rollback();
            throw new GenericException(
                    2,
                    ErrorStatus.UNAUTHORIZED,
                    ErrorLevel.ERROR,
                    ex.getMessage(),
                    "Ocurrió un error al obtener la listar las cuentas de usuario. Por favor, comunícate con el administrador"
            );
        } finally {
            this.connectionPort.close();
        }
    }

    public List<AccountUserDto> getAccountUsers(){
        List<AccountUserEntity> accountUserEntities = this.accountUserPort.getAccountUsers();
        return this.accountUserMapper.convertAccountUserEntitiesToAccountUserDtos(accountUserEntities);
    }
}
