package com.sigma.task.sigmatesttask.repositories;

import com.sigma.task.sigmatesttask.entities.ParkingSpotEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingSpotRepository extends CrudRepository<ParkingSpotEntity, Long> {

}
