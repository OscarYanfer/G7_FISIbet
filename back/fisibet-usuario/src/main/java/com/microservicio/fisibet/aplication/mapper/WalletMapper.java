package com.microservicio.fisibet.aplication.mapper;

import com.microservicio.fisibet.aplication.dto.WalletDto;
import com.microservicio.fisibet.domain.entity.WalletEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WalletMapper {
    WalletDto convertWalletEntityToWalletDto(WalletEntity walletEntity);
    WalletEntity convertWalletDtoToWalletEntity(WalletDto walletDto);

}
