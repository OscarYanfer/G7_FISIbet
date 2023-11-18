package com.microservicio.fisibet.aplication.usecase;

import com.microservicio.fisibet.aplication.dto.AccountUserDto;
import com.microservicio.fisibet.aplication.mapper.AccountUserMapper;
import com.microservicio.fisibet.aplication.port.AccountUserPort;
import com.microservicio.fisibet.aplication.port.ConnectionPort;
import com.microservicio.fisibet.domain.entity.AccountUserEntity;
import com.microservicio.fisibet.domain.exception.ErrorLevel;
import com.microservicio.fisibet.domain.exception.ErrorStatus;
import com.microservicio.fisibet.domain.exception.GenericException;

public class UpdateAccountUserUseCase {
    private AccountUserPort accountUserPort;
    private AccountUserMapper accountUserMapper;
    private ConnectionPort connectionPort;
    public UpdateAccountUserUseCase(AccountUserMapper accountUserMapper,
                                    AccountUserPort accountUserPort,
                                    ConnectionPort connectionPort){
        this.accountUserMapper = accountUserMapper;
        this.accountUserPort = accountUserPort;
        this.connectionPort = connectionPort;
    }

    public AccountUserDto run(AccountUserDto request, Integer id) throws GenericException {
        try{
            this.connectionPort.begin();
            AccountUserDto accountUserDto = updateAccountUser(request, id);

            this.connectionPort.commit();
            return accountUserDto;
        }catch (Exception ex){
            this.connectionPort.rollback();
            throw new GenericException(
                    2,
                    ErrorStatus.UNAUTHORIZED,
                    ErrorLevel.ERROR,
                    ex.getMessage(),
                    "Ocurrió un error al actualizar la cuenta. Por favor, comunícate con el administrador"
            );
        } finally {
            this.connectionPort.close();
        }
    }

    public AccountUserDto updateAccountUser(AccountUserDto request, Integer id){
        AccountUserEntity accountUserEntity = this.accountUserMapper.convertAccountUserDtoToAccountUserEntity(request);
        return this.accountUserMapper.convertAccountUserEntityToAccountUserDto(this.accountUserPort.updateAccountUserById(accountUserEntity, id));
    };
}
