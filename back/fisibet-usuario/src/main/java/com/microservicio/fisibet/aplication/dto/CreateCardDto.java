package com.microservicio.fisibet.aplication.dto;

public class CreateCardDto {
    private Integer idAccount;
    private String cardNumber;
    private String name;
    private String surname;
    private Integer cvv;
    private String expirationMonthYear;

    public Integer getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Integer idAccount) {
        this.idAccount = idAccount;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public String getExpirationMonthYear() {
        return expirationMonthYear;
    }

    public void setExpirationMonthYear(String expirationMonthYear) {
        this.expirationMonthYear = expirationMonthYear;
    }
}
