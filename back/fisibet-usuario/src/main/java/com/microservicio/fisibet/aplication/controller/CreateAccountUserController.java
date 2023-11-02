package com.microservicio.fisibet.aplication.controller;


import com.microservicio.fisibet.aplication.dto.AccountUserDto;
import com.microservicio.fisibet.aplication.dto.CreateAccountUserDto;
import com.microservicio.fisibet.aplication.mapper.AccountUserMapper;
import com.microservicio.fisibet.aplication.request.CreateAccountUserRequest;
import com.microservicio.fisibet.aplication.response.BaseResponse;
import com.microservicio.fisibet.aplication.response.CreateAccountUserResponse;
import com.microservicio.fisibet.aplication.usecase.CreateAccountUserUseCase;
import com.microservicio.fisibet.domain.exception.GenericException;
import com.microservicio.fisibet.infraestructure.port.AccountUserMySQLPort;
import com.microservicio.fisibet.infraestructure.port.ConnectionMySQLPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping(path = "/accountUser")
public class CreateAccountUserController {
    @Autowired
    private AccountUserMySQLPort accountUserMySQLPort;
    @Autowired
    private AccountUserMapper accountUserMapper;
    @Autowired
    private ConnectionMySQLPort connectionMySQLPort;


    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BaseResponse<CreateAccountUserResponse>> createAccountUser(
            @Validated @RequestBody CreateAccountUserRequest request
    ) throws GenericException, IOException {

        CreateAccountUserDto createAccountUserDto = this.accountUserMapper.convertCreateAccountUserRequestToCreateAccountUserDto(request);
        CreateAccountUserUseCase createAccountUserUseCase = new CreateAccountUserUseCase(accountUserMapper, accountUserMySQLPort, connectionMySQLPort);

        AccountUserDto accountUserDto = createAccountUserUseCase.run(createAccountUserDto);

        CreateAccountUserResponse createBetResponse = this.accountUserMapper.convertAccountUserDtoCreateAccountUserResponse(accountUserDto);
        BaseResponse<CreateAccountUserResponse> response = new BaseResponse();
        response.setMessage("Se ha creado exitosamente la apuesta");
        response.setContent(createBetResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
