package com.microservicio.fisibet.aplication.response;

public class AuthResponse {
    private String username;
    private String profile;
    private Integer conectado;

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

    public Integer getConectado() {
        return conectado;
    }

    public void setConectado(Integer conectado) {
        this.conectado = conectado;
    }
}
