package com.microservicio.fisibet.aplication.response;

public class BaseResponse <T>{
    private  String message = null;
    private T content = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
