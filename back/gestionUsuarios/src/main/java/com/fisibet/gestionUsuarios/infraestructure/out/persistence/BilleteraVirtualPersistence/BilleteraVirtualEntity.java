package com.fisibet.gestionUsuarios.infraestructure.out.persistence.BilleteraVirtualPersistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "BilleteraVirtual")
public class BilleteraVirtualEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBilleteraVirtual;

    private float saldo;

    private String registered;

    private String updated;

}
