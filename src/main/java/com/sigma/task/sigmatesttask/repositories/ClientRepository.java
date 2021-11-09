package com.sigma.task.sigmatesttask.repositories;

import com.sigma.task.sigmatesttask.entities.ClientEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<ClientEntity, Long> {

}
