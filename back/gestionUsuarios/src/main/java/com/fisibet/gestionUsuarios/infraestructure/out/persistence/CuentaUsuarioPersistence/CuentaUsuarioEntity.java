package com.fisibet.gestionUsuarios.infraestructure.out.persistence.CuentaUsuarioPersistence;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "cuenta_usuario")
public class CuentaUsuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String correo;

    private String contrasenia;

    private String registered;

    private String updated;

    //@OneToOne
    //private Usuario usuario;

    public CuentaUsuarioEntity(int id, String correo, String contra){
        this.id = id;
        this.correo = correo;
        this.contrasenia = contra;
    }
}
