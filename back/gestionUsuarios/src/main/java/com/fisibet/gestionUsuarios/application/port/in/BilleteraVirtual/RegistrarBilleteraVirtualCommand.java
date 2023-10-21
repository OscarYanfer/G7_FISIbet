package com.fisibet.gestionUsuarios.application.port.in.BilleteraVirtual;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrarBilleteraVirtualCommand {
    int idBillteraVirtual;

    float saldo;

    String registered;

    String updated;

}
