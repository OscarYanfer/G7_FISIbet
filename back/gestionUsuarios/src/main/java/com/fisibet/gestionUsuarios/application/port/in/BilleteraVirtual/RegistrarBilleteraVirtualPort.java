package com.fisibet.gestionUsuarios.application.port.in.BilleteraVirtual;

import com.fisibet.gestionUsuarios.domain.BilleteraVirtual;

public interface RegistrarBilleteraVirtualPort {
    public void actualizarBilleteraVirtual(RegistrarBilleteraVirtualCommand registrarBilleteraVirtualCommand);

    public void registrarBilleteraVirtual(BilleteraVirtual billeteraVirtual);
}
