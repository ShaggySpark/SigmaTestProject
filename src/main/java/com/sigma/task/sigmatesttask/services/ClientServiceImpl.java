package com.sigma.task.sigmatesttask.services;

import com.sigma.task.sigmatesttask.entities.ClientEntity;
import com.sigma.task.sigmatesttask.entities.ParkingSpotEntity;
import com.sigma.task.sigmatesttask.exceptions.NotFoundException;
import com.sigma.task.sigmatesttask.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public class ClientServiceImpl implements ClientService{

    @Autowired
    private ClientRepository clientDao;
    @Autowired
    private ParkingSpotService parkingSpotService;
    @Autowired
    private ParkingRateService parkingRateService;

    @Override
    public List<ClientEntity> clientsList() {
        return (List<ClientEntity>) clientDao.findAll();
    }

    @Override
    public ClientEntity findById(Long id) {
        Optional<ClientEntity> client = clientDao.findById(id);
        if (!client.isPresent()) {
            throw new NotFoundException();
        }
        return client.get();
    }

    @Override
    public ClientEntity createClient(ClientEntity clientEntity) {
        return clientDao.save(clientEntity);
    }

    @Override
    @Transactional
    public ClientEntity updateClient(Long id, ClientEntity clientEntity) {
        Optional<ClientEntity> client = clientDao.findById(id);
        if (!client.isPresent()) {
            throw new NotFoundException();
        }
        client.get().setParkingSpotEntity(clientEntity.getParkingSpotEntity());
        client.get().setParkingRateEntity(clientEntity.getParkingRateEntity());
        client.get().setFullName(clientEntity.getFullName());
        client.get().setCarCode(clientEntity.getCarCode());
        return clientDao.save(client.get());
    }

    @Override
    public void deleteById(Long id) {
        clientDao.deleteById(id);
    }

    @Override
    @Transactional
    public ParkingSpotEntity takeParkingSpot(Long clientId, int timeNeeded) {
        Optional<ClientEntity> client = clientDao.findById(clientId);
        if (!client.isPresent()) {
            throw new NotFoundException();
        }
        client.get().setParkingRateEntity(parkingRateService.getParkingRate(timeNeeded));
        ParkingSpotEntity parkingSpotEntity = parkingSpotService.findFreeParking();
        parkingSpotEntity.setEmpty(false);
        parkingSpotService.updateParkingSpot(parkingSpotEntity.getId(), parkingSpotEntity);
        client.get().setParkingSpotEntity(parkingSpotEntity);
        clientDao.save(client.get());
        return parkingSpotEntity;
    }

    @Override
    @Transactional
    public void clearAllOrders() {
        List<ClientEntity> clients = (List<ClientEntity>) clientDao.findAll();
        for (ClientEntity client : clients){
            client.setParkingSpotEntity(null);
            client.setParkingRateEntity(null);
            clientDao.save(client);
        }
    }


}
