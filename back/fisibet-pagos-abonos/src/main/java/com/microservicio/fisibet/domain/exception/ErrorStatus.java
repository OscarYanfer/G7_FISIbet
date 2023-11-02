package com.microservicio.fisibet.domain.exception;

public enum ErrorStatus {
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    INTERNAL_ERROR(500),
    SERVICE_UNAVAILABLE(503);
    private final int value;

    ErrorStatus(final int newValue) {
        value = newValue;
    }

    public int getValue() {
        return value;
    }
}
