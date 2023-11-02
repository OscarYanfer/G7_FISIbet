package com.microservicio.fisibet.infraestructure.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "card")
public class CardModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "idAccount")
    private Integer idAccount;
    @Column(name = "cardNumber")
    private String cardNumber;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "cvv")
    private Integer cvv;
    @Column(name = "expirationMonthYear")
    private String expirationMonthYear;
    @Column(name = "status")
    private Integer status;
    @Column(name = "registeredOn")
    private LocalDateTime registeredOn;
    @Column(name = "updatedOn")
    private LocalDateTime updatedOn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(LocalDateTime registeredOn) {
        this.registeredOn = registeredOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }

    @Override
    public String toString() {
        return "WalletModel{" +
                "id=" + id +
                ", idAccount=" + idAccount +
                ", cardNumber='" + cardNumber + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", cvv=" + cvv +
                ", expirationMonthYear='" + expirationMonthYear + '\'' +
                ", status=" + status +
                ", registeredOn=" + registeredOn +
                ", updatedOn=" + updatedOn +
                '}';
    }
}
