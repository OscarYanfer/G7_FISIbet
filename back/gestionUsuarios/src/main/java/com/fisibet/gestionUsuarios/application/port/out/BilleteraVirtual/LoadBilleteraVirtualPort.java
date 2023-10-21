package com.fisibet.gestionUsuarios.application.port.out.BilleteraVirtual;

import com.fisibet.gestionUsuarios.domain.BilleteraVirtual;

public interface LoadBilleteraVirtualPort {
    BilleteraVirtual loadById(int id);
}
