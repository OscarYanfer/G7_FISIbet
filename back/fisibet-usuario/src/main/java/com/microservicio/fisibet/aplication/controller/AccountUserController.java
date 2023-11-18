package com.microservicio.fisibet.aplication.controller;

import com.microservicio.fisibet.aplication.dto.AccountUserDto;
import com.microservicio.fisibet.aplication.dto.CreateAccountUserDto;
import com.microservicio.fisibet.aplication.mapper.AccountUserMapper;
import com.microservicio.fisibet.aplication.request.AccountUserRequest;
import com.microservicio.fisibet.aplication.request.CreateAccountUserRequest;
import com.microservicio.fisibet.aplication.request.UpdateAccountUserRequest;
import com.microservicio.fisibet.aplication.response.AccountUserResponse;
import com.microservicio.fisibet.aplication.response.BaseResponse;
import com.microservicio.fisibet.aplication.response.CreateAccountUserResponse;
import com.microservicio.fisibet.aplication.usecase.CreateAccountUserUseCase;
import com.microservicio.fisibet.aplication.usecase.GetAccountUserByIdUseCase;
import com.microservicio.fisibet.aplication.usecase.GetAccountUsersUseCase;
import com.microservicio.fisibet.aplication.usecase.UpdateAccountUserUseCase;
import com.microservicio.fisibet.domain.exception.GenericException;
import com.microservicio.fisibet.infraestructure.port.AccountUserMySQLPort;
import com.microservicio.fisibet.infraestructure.port.ConnectionMySQLPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/accountUser")
public class AccountUserController {
    @Autowired
    private AccountUserMySQLPort accountUserMySQLPort;
    @Autowired
    private AccountUserMapper accountUserMapper;
    @Autowired
    private ConnectionMySQLPort connectionMySQLPort;


    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BaseResponse<AccountUserResponse>> createAccountUser(
            @Validated @RequestBody AccountUserRequest request
    ) throws GenericException, IOException {

        AccountUserDto accountUserDto = this.accountUserMapper.convertAccountUserRequestToAccountUserDto(request);
        CreateAccountUserUseCase createAccountUserUseCase = new CreateAccountUserUseCase(accountUserMapper, accountUserMySQLPort, connectionMySQLPort);

        AccountUserDto accountUserDto1 = createAccountUserUseCase.run(accountUserDto);

        AccountUserResponse accountUserResponse = this.accountUserMapper.convertAccountUserDtoToAccountUserResponse(accountUserDto1);
        BaseResponse<AccountUserResponse> response = new BaseResponse();
        response.setMessage("Se ha creado exitosamente la cuenta");
        response.setContent(accountUserResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<BaseResponse<AccountUserResponse>> getAccountUserById(
            @PathVariable Integer id
    ) throws GenericException {
        GetAccountUserByIdUseCase getAccountUserByIdUseCase = new GetAccountUserByIdUseCase(accountUserMapper, accountUserMySQLPort, connectionMySQLPort);
        AccountUserDto accountUserDto = getAccountUserByIdUseCase.run(id);

        AccountUserResponse accountUserResponse = this.accountUserMapper.convertAccountUserDtoToAccountUserResponse(accountUserDto);

        BaseResponse response = new BaseResponse();
        response.setMessage("Datos de la cuenta");
        response.setContent(accountUserResponse);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/all")
    public @ResponseBody ResponseEntity<BaseResponse<List<AccountUserResponse>>> getAccountUserById(
    ) throws GenericException {

        GetAccountUsersUseCase getAccountUsersUseCase = new GetAccountUsersUseCase(accountUserMapper, accountUserMySQLPort, connectionMySQLPort);
        List<AccountUserDto> accountUserDtos = getAccountUsersUseCase.run();

        List<AccountUserResponse> accountUserResponse = this.accountUserMapper.convertAccountUserDtosToAccountUserResponses(accountUserDtos);

        BaseResponse<List<AccountUserResponse>> response = new BaseResponse();
        response.setMessage("Lista de cuentas de usuarios");
        response.setContent(accountUserResponse);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<BaseResponse<AccountUserResponse>> updateAccountUserById(
            @PathVariable Integer id, @RequestBody UpdateAccountUserRequest request
            ) throws GenericException {
        AccountUserDto accountUserDto = this.accountUserMapper.convertUpdateAccountUserRequestToAccountUserDto(request);

        UpdateAccountUserUseCase updateAccountUserUseCase = new UpdateAccountUserUseCase(accountUserMapper, accountUserMySQLPort, connectionMySQLPort);
        AccountUserDto accountUserDtoNew = updateAccountUserUseCase.run(accountUserDto,id);

        AccountUserResponse accountUserResponse = this.accountUserMapper.convertAccountUserDtoToAccountUserResponse(accountUserDtoNew);

        BaseResponse response = new BaseResponse();
        response.setMessage("Datos actualizados de la cuenta");
        response.setContent(accountUserResponse);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
