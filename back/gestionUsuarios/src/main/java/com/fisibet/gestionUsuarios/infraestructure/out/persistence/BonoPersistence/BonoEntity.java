package com.fisibet.gestionUsuarios.infraestructure.out.persistence.BonoPersistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Bono")
public class BonoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBono;

    private String nombreBono;

    private int estado;

    private String registered;

    private String updated;
}


