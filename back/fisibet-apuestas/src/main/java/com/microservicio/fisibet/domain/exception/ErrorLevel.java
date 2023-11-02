package com.microservicio.fisibet.domain.exception;

public enum ErrorLevel {
    FATAL(1),
    CRITICAL(2),
    ERROR(3),
    WARNING(4),
    INFO(5);

    private final int value;

    ErrorLevel(final int newValue) {
        value = newValue;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        switch (this.value){
            case 0:
                return "fatal";
            case 1:
                return "critical";
            case 2:
                return "error";
            case 3:
                return "warning";
            default:
                return "info";
        }
    }
}
