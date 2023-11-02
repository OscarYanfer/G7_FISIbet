package com.microservicio.fisibet.infraestructure.model;

import javax.persistence.*;

@Entity
@Table(name = "transaccion")
public class TransactionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

}
