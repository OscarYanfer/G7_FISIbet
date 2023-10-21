package com.fisibet.gestionUsuarios.infraestructure.out.persistence.TipoUsuarioPersistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "TipoUsuario")
public class TipoUsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombreTipoUsuario;

    private String registered;

    private String updated;
}
