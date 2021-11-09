package com.sigma.task.sigmatesttask.repositories;

import com.sigma.task.sigmatesttask.entities.ParkingRateEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRateRepository extends CrudRepository<ParkingRateEntity, Long> {

}
