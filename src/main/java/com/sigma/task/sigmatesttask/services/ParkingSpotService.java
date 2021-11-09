package com.sigma.task.sigmatesttask.services;

import com.sigma.task.sigmatesttask.entities.ParkingSpotEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ParkingSpotService {
    List<ParkingSpotEntity> listParkingSpots ();
    ParkingSpotEntity findById(Long id);
    ParkingSpotEntity createParkingSpot(ParkingSpotEntity parkingSpotEntity);
    ParkingSpotEntity updateParkingSpot(Long id, ParkingSpotEntity parkingSpotEntity);
    ParkingSpotEntity findFreeParking();
    void deleteById(Long id);

}
