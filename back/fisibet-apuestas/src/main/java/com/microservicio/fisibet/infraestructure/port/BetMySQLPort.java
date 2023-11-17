package com.microservicio.fisibet.infraestructure.port;

import com.microservicio.fisibet.aplication.port.BetPort;
import com.microservicio.fisibet.domain.entity.BetEntity;
import com.microservicio.fisibet.infraestructure.mapper.BetInfraMapper;
import com.microservicio.fisibet.infraestructure.model.BetModel;
import com.microservicio.fisibet.infraestructure.port.spring.BetSpringPort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BetMySQLPort implements BetPort {
    private BetInfraMapper betInfraMapper;
    private BetSpringPort betSpringPort;
    public BetMySQLPort(BetInfraMapper betInfraMapper,
                        BetSpringPort betSpringPort){
        this.betInfraMapper = betInfraMapper;
        this.betSpringPort = betSpringPort;
    }
    @Override
    public Integer registerBet(BetEntity betEntity) {
        betEntity.setStatus(1);
        betEntity.setRegisteredOn(LocalDateTime.now());
        BetModel betModel = this.betInfraMapper.convertBetEntityToBetModel(betEntity);
        BetModel newBetModel = this.betSpringPort.save(betModel);
        return newBetModel.getId();
    }

    @Override
    public List<BetEntity> getBets() {
        List<BetModel> betModels = this.betSpringPort.getBetsEnabled();
        return this.betInfraMapper.convertBetModelsToBetEntities(betModels);
    }

    @Override
    public BetEntity updateBetByEvent(BetEntity betEntity) {
        BetModel betModel = this.betSpringPort.findById(betEntity.getId()).orElse(null);;
        betModel.setName(betEntity.getName());
        betModel.setPay(betEntity.getPay());
        betModel.setStatus(1);
        betModel.setUpdatedOn(LocalDateTime.now());
        return this.betInfraMapper.convertBetModelToBetEntity(this.betSpringPort.save(betModel));
    }

    @Override
    public List<BetEntity> getBetsByEventId(Integer eventId) {
        List<BetModel> betModels = this.betSpringPort.getBetByEventId(eventId);
        return this.betInfraMapper.convertBetModelsToBetEntities(betModels);
    }
}
