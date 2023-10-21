package com.ms.gestionapuestas.domain.exception;

import java.time.LocalDateTime;

public class GenericException extends Exception{
    private LocalDateTime datetime = LocalDateTime.now();
    private Integer id;
    private ErrorStatus status;
    private ErrorLevel errorLevel;
    private String errorType = "Generic System Error";
    private String userMessage;

    public GenericException(String message){
        super(message);
        this.status = ErrorStatus.INTERNAL_ERROR;
    }

    public GenericException(Throwable cause){
        super(cause);
        this.status = ErrorStatus.INTERNAL_ERROR;
    }

    public GenericException(Integer id, ErrorStatus status, ErrorLevel errorLevel, String message, String userMessage){

        super(message);

        this.id = id;
        this.status = status;
        this.errorLevel = errorLevel;
        this.userMessage = userMessage;
    }

    public GenericException(Integer id, ErrorStatus status, ErrorLevel errorLevel, String message, String userMessage, Throwable cause){

        super(message, cause);

        this.id = id;
        this.status = status;
        this.errorLevel = errorLevel;
        this.userMessage = userMessage;
    }

    public GenericException(Integer id, ErrorStatus status, ErrorLevel errorLevel, String message, String userMessage, String errorType){

        super(message);

        this.id = id;
        this.status = status;
        this.errorLevel = errorLevel;
        this.userMessage = userMessage;
        this.errorType = errorType;
    }

    public GenericException(Integer id, ErrorStatus status, ErrorLevel errorLevel, String message, String userMessage, String errorType,  Throwable cause){

        super(message, cause);

        this.id = id;
        this.status = status;
        this.errorLevel = errorLevel;
        this.userMessage = userMessage;
        this.errorType = errorType;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public Integer getId() {
        return id;
    }

    public ErrorStatus getStatus() {
        return status;
    }

    public ErrorLevel getErrorLevel() {
        return errorLevel;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public String getErrorType(){
        return errorType;
    }
}
