package com.microservicio.fisibet.aplication.port;

public interface ConnectionPort {
    void begin();
    void commit();
    void rollback();
    void close();
}
