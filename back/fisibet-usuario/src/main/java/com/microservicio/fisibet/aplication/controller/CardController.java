package com.microservicio.fisibet.aplication.controller;

import com.microservicio.fisibet.aplication.dto.CardDto;
import com.microservicio.fisibet.aplication.dto.CreateCardDto;
import com.microservicio.fisibet.aplication.mapper.CardMapper;
import com.microservicio.fisibet.aplication.request.CreateCardRequest;
import com.microservicio.fisibet.aplication.response.BaseResponse;
import com.microservicio.fisibet.aplication.response.CardResponse;
import com.microservicio.fisibet.aplication.usecase.CreateCardUseCase;
import com.microservicio.fisibet.domain.exception.GenericException;
import com.microservicio.fisibet.infraestructure.port.ConnectionMySQLPort;
import com.microservicio.fisibet.infraestructure.port.CardMySQLPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(path = "/card")
public class CardController {

    @Autowired
    ConnectionMySQLPort connectionMySQLPort;
    @Autowired
    CardMySQLPort cardMySQLPort;
    @Autowired
    CardMapper cardMapper;

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<BaseResponse<CardResponse>> registerCard(
            @Validated @RequestBody CreateCardRequest request
    ) throws GenericException, IOException {

        CreateCardDto createCardDto = this.cardMapper.convertCreateCardRequestToCreateCardDto(request);
        CreateCardUseCase createCardUseCase = new CreateCardUseCase(connectionMySQLPort, cardMapper, cardMySQLPort);

        CardDto cardDto = createCardUseCase.run(createCardDto);

        CardResponse cardResponse = this.cardMapper.convertCardDtoToCardResponse(cardDto);
        BaseResponse<CardResponse> response = new BaseResponse();
        response.setMessage("Se ha registrado exitosamente la tarjeta");
        response.setContent(cardResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
