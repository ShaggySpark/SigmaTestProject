package com.sigma.task.sigmatesttask.services;

import com.sigma.task.sigmatesttask.entities.ParkingRateEntity;
import com.sigma.task.sigmatesttask.entities.ParkingSpotEntity;
import com.sigma.task.sigmatesttask.exceptions.NotFoundException;
import com.sigma.task.sigmatesttask.repositories.ParkingRateRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class ParkingRateServiceImpl implements ParkingRateService{

    @Autowired
    private ParkingRateRepository parkingRateDao;


    @Override
    public List<ParkingRateEntity> listParkingRates() {
        List<ParkingRateEntity> allParkingRates = (List<ParkingRateEntity>) parkingRateDao.findAll();
        return allParkingRates;
    }

    @Override
    public ParkingRateEntity findById(Long id) {
        Optional<ParkingRateEntity> parkingRate = parkingRateDao.findById(id);
        if (!parkingRate.isPresent()) {
            throw new NotFoundException();
        }
        return parkingRate.get();
    }

    @Override
    public ParkingRateEntity createParkingRate(ParkingRateEntity parkingRateEntity) {
        ParkingRateEntity newEntity = parkingRateDao.save(parkingRateEntity);
        return newEntity;
    }

    @Override
    @Transactional
    public ParkingRateEntity updateParkingRate(Long id, ParkingRateEntity parkingRateEntity) {
        Optional<ParkingRateEntity> parkingRate = parkingRateDao.findById(id);
        if (!parkingRate.isPresent()) {
            throw new NotFoundException();
        }
        parkingRate.get().setPricePerMinute(parkingRateEntity.getPricePerMinute());
        parkingRate.get().setMinTime(parkingRateEntity.getMinTime());
        return parkingRateDao.save(parkingRate.get());
    }

    @Override
    public ParkingRateEntity getParkingRate(int minutesNeeded) {
        List<ParkingRateEntity> allParkingRates = (List<ParkingRateEntity>) parkingRateDao.findAll();
        ParkingRateEntity result = null;
        allParkingRates.sort((p1, p2) -> p2.getMinTime()- p1.getMinTime());
        for(ParkingRateEntity parkingRate : allParkingRates){
            if (minutesNeeded > parkingRate.getMinTime()){
                result = parkingRate;
                break;
            }
        }
        return result;
    }

    @Override
    public void deleteById(Long id) {
        parkingRateDao.deleteById(id);
    }

}
