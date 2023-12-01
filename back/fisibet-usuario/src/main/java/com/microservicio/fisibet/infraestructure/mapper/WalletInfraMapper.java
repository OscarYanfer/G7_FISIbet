package com.microservicio.fisibet.infraestructure.mapper;

import com.microservicio.fisibet.domain.entity.WalletEntity;
import com.microservicio.fisibet.infraestructure.model.WalletModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WalletInfraMapper {
    WalletModel convertWalletEntityToWalletModel(WalletEntity walletEntity);
    WalletEntity convertWalletModelToWalletEntity(WalletModel walletModel);
}
