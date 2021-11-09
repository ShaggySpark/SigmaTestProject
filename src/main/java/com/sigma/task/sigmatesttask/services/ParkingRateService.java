package com.sigma.task.sigmatesttask.services;

import com.sigma.task.sigmatesttask.entities.ParkingRateEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ParkingRateService {
    List<ParkingRateEntity> listParkingRates ();
    ParkingRateEntity findById(Long id);
    ParkingRateEntity createParkingRate(ParkingRateEntity parkingRateEntity);
    ParkingRateEntity updateParkingRate(Long id, ParkingRateEntity parkingRateEntity);
    ParkingRateEntity getParkingRate(int minutesNeeded);
    void deleteById(Long id);
}
