package com.sigma.task.sigmatesttask.services;

import com.sigma.task.sigmatesttask.entities.ClientEntity;
import com.sigma.task.sigmatesttask.entities.ParkingSpotEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {
    List<ClientEntity> clientsList ();
    ClientEntity findById(Long id);
    ClientEntity createClient(String name, String carCode);
    ClientEntity updateClient(Long id, ClientEntity clientEntity);
    void deleteById(Long id);
    ClientEntity freeParkingSpot(Long id);
    ParkingSpotEntity takeParkingSpot(Long clientId, int timeNeeded);
    void clearAllOrders();

}
