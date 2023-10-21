package com.fisibet.gestionUsuarios.infraestructure.out.persistence.BilleteraVirtualPersistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringBilleteraVirtualRepository extends JpaRepository<BilleteraVirtualEntity, Integer> {
    BilleteraVirtualEntity findByIdBilleteraVirtual(int id);
}
