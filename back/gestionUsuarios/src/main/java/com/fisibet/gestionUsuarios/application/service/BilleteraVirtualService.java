package com.fisibet.gestionUsuarios.application.service;

import com.fisibet.gestionUsuarios.application.common.UseCase;
import com.fisibet.gestionUsuarios.application.port.in.BilleteraVirtual.RegistrarBilleteraVirtualCommand;
import com.fisibet.gestionUsuarios.application.port.in.BilleteraVirtual.RegistrarBilleteraVirtualPort;
import com.fisibet.gestionUsuarios.application.port.out.BilleteraVirtual.LoadBilleteraVirtualPort;
import com.fisibet.gestionUsuarios.application.port.out.BilleteraVirtual.SaveBillteraVirtualPort;
import com.fisibet.gestionUsuarios.application.port.out.BilleteraVirtual.UpdateBilleteraVirtualPort;
import com.fisibet.gestionUsuarios.domain.BilleteraVirtual;
import jakarta.transaction.Transactional;

@UseCase
public class BilleteraVirtualService implements RegistrarBilleteraVirtualPort {

    private final LoadBilleteraVirtualPort loadBilleteraVirtualPort;

    private final SaveBillteraVirtualPort saveBillteraVirtualPort;

    private final UpdateBilleteraVirtualPort updateBilleteraVirtualPort;

    public BilleteraVirtualService(LoadBilleteraVirtualPort loadBilleteraVirtualPort, SaveBillteraVirtualPort billteraVirtualPort, UpdateBilleteraVirtualPort updateBilleteraVirtualPort) {
        this.loadBilleteraVirtualPort = loadBilleteraVirtualPort;
        this.saveBillteraVirtualPort = billteraVirtualPort;
        this.updateBilleteraVirtualPort = updateBilleteraVirtualPort;
    }

    @Override
    public void actualizarBilleteraVirtual(RegistrarBilleteraVirtualCommand registrarBilleteraVirtualCommand) {

    }

    @Transactional
    @Override
    public void registrarBilleteraVirtual(BilleteraVirtual billeteraVirtual) {
        try{
            BilleteraVirtual billeteraVirtual1 = loadBilleteraVirtualPort.loadById(billeteraVirtual.getIdBillteraVirtual());
            if(billeteraVirtual1 == null){
                saveBillteraVirtualPort.save(billeteraVirtual);
            } else {
                throw new RuntimeException("Billetera ya registrada");
            }
        } catch (Exception e) {
            System.out.println("Error BilleteraVirtualService: " + e.getMessage());
        }
    }
}
