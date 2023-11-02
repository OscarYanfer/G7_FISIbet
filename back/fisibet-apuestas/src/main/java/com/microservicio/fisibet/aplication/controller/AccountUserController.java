package com.microservicio.fisibet.aplication.controller;

import com.microservicio.fisibet.aplication.mapper.AccountUserMapper;
import com.microservicio.fisibet.aplication.response.AccountUserResponse;
import com.microservicio.fisibet.aplication.response.BaseResponse;
import com.microservicio.fisibet.domain.exception.GenericException;
import com.microservicio.fisibet.infraestructure.port.AccountUserMySQLPort;
import com.microservicio.fisibet.infraestructure.port.ConnectionMySQLPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(path = "/accountUser")
public class AccountUserController {
    @Autowired
    private ConnectionMySQLPort connectionMySQLPort;
    @Autowired
    private AccountUserMapper accountUserMapper;
    @Autowired
    private AccountUserMySQLPort accountUserMySQLPort;

    @GetMapping(path = "/all")
    public @ResponseBody ResponseEntity<BaseResponse<List<AccountUserResponse>>> getAccountUsers() throws GenericException {
        //GetAccountUsersUseCase getAccountUsersUseCase = new GetAccountUsersUseCase(accountUserMapper,
          //      connectionMySQLPort, accountUserMySQLPort);
        //List<AccountUserDto> accountUserDtos = getAccountUsersUseCase.run();

        //List<AccountUserResponse> accountUserResponses = this.accountUserMapper.convertAccountUserDtosToAccountUserResponses(accountUserDtos);

        BaseResponse response = new BaseResponse();
        response.setMessage("Lista de cuentas de usuario");
        response.setContent(null);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
