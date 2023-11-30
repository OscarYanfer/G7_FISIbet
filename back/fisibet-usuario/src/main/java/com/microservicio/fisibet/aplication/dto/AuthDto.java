package com.microservicio.fisibet.aplication.dto;

public class AuthDto {
    private String username;
    private String password;
    private String profile;
    private String conectado;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConectado() {
        return conectado;
    }

    public void setConectado(String conectado) {
        this.conectado = conectado;
    }
}
