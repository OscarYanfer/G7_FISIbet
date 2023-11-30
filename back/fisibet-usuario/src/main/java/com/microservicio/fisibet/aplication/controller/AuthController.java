package com.microservicio.fisibet.aplication.controller;

import com.microservicio.fisibet.aplication.dto.AccountUserDto;
import com.microservicio.fisibet.aplication.dto.AuthDto;
import com.microservicio.fisibet.aplication.mapper.SessionUserMapper;
import com.microservicio.fisibet.aplication.request.AccountUserRequest;
import com.microservicio.fisibet.aplication.request.AuthRequest;
import com.microservicio.fisibet.aplication.response.AccountUserResponse;
import com.microservicio.fisibet.aplication.response.AuthResponse;
import com.microservicio.fisibet.aplication.response.BaseResponse;
import com.microservicio.fisibet.aplication.usecase.CreateAccountUserUseCase;
import com.microservicio.fisibet.aplication.usecase.LoginUseCase;
import com.microservicio.fisibet.domain.exception.GenericException;
import com.microservicio.fisibet.infraestructure.port.ConnectionMySQLPort;
import com.microservicio.fisibet.infraestructure.port.SessionUserMySQLPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {
    private SessionUserMySQLPort sessionUserMySQLPort;
    private SessionUserMapper sessionUserMapper;
    private ConnectionMySQLPort connectionMySQLPort;
    public AuthController(SessionUserMySQLPort sessionUserMySQLPort,
                          SessionUserMapper sessionUserMapper,
                          ConnectionMySQLPort connectionMySQLPort){
        this.sessionUserMapper = sessionUserMapper;
        this.sessionUserMySQLPort = sessionUserMySQLPort;
        this.connectionMySQLPort = connectionMySQLPort;
    }

    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BaseResponse<AuthResponse>> login(
            @Validated @RequestBody AuthRequest request
    ) throws GenericException, IOException {

        AuthDto authDto = this.sessionUserMapper.convertAuthRequestToAuthDto(request);
        LoginUseCase loginUseCase = new LoginUseCase(sessionUserMapper, sessionUserMySQLPort, connectionMySQLPort);

        AuthDto authDto1 = loginUseCase.run(authDto);

        AuthResponse authResponse = this.sessionUserMapper.convertAuthDtoToAuthResponse(authDto1);
        BaseResponse<AuthResponse> response = new BaseResponse();
        response.setMessage("Se iniciado session exitosamente");
        response.setContent(authResponse);

        if(authResponse == null){
            response.setMessage("El usuario no puede iniciar sessión, ya se encuentra conectado");
        }

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
