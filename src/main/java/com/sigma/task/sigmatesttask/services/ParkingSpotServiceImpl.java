package com.sigma.task.sigmatesttask.services;

import com.sigma.task.sigmatesttask.repositories.ParkingSpotRepository;
import com.sigma.task.sigmatesttask.entities.ParkingSpotEntity;
import com.sigma.task.sigmatesttask.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingSpotServiceImpl implements ParkingSpotService {
    @Autowired
    ParkingSpotRepository parkingSpotDao;

    //Метод выдает список всех парковочных мест
    @Override
    public List<ParkingSpotEntity> listParkingSpots() {
        List<ParkingSpotEntity> allParkingSpots = (List<ParkingSpotEntity>) parkingSpotDao.findAll();
        return allParkingSpots;
    }

    //Метод ищет парковочное место по id
    @Override
    public ParkingSpotEntity findById(Long id) {
        Optional<ParkingSpotEntity> parkingSpot = parkingSpotDao.findById(id);
        if (!parkingSpot.isPresent()) {
            throw new NotFoundException();
        }
        return parkingSpot.get();
    }

    //Метод для создания парковочного места
    @Override
    public ParkingSpotEntity createParkingSpot(ParkingSpotEntity parkingSpotEntity) {
        ParkingSpotEntity newEntity = parkingSpotDao.save(parkingSpotEntity);
        return newEntity;
    }

    //Метод для изменения парковочного места
    @Override
    @Transactional
    public ParkingSpotEntity updateParkingSpot(Long id, ParkingSpotEntity parkingSpotEntity) {
        Optional<ParkingSpotEntity> parkingSpot = parkingSpotDao.findById(id);
        if (!parkingSpot.isPresent()) {
            throw new NotFoundException();
        }
        parkingSpot.get().setCode(parkingSpotEntity.getCode());
        parkingSpot.get().setEmpty(parkingSpotEntity.isEmpty());

        //parkingSpotDao.update...
        return parkingSpotDao.save(parkingSpot.get());
    }

    //Метод для поиска первого свободного парковочного места
    @Override
    public ParkingSpotEntity findFreeParking() {
        List<ParkingSpotEntity> allParkingSpots = (List<ParkingSpotEntity>) parkingSpotDao.findAll();
        ParkingSpotEntity result = null;
        for (ParkingSpotEntity parkingSpotEntity : allParkingSpots){
            if (parkingSpotEntity.isEmpty())
                result = parkingSpotEntity;
        }
        if (result==null)
            throw new NotFoundException();
        return result;
    }

    @Override
    public void deleteById(Long id) {
        parkingSpotDao.deleteById(id);
    }


}
