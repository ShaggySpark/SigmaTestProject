package com.sigma.task.sigmatesttask.services;

import com.sigma.task.sigmatesttask.entities.ClientEntity;
import com.sigma.task.sigmatesttask.entities.ParkingSpotEntity;
import com.sigma.task.sigmatesttask.exceptions.NotFoundException;
import com.sigma.task.sigmatesttask.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService{

    @Autowired
    private ClientRepository clientDao;
    @Autowired
    private ParkingSpotService parkingSpotService;
    @Autowired
    private ParkingRateService parkingRateService;

    //Метод выдает список всех клиентов
    @Override
    public List<ClientEntity> clientsList() {
        return (List<ClientEntity>) clientDao.findAll();
    }

    //Метод выдает конкретного клиента по его id
    @Override
    public ClientEntity findById(Long id) {
        Optional<ClientEntity> client = clientDao.findById(id);
        if (!client.isPresent()) {
            throw new NotFoundException();
        }
        return client.get();
    }

    //Метод создает клиента и заносит его в базу данных
    @Override
    public ClientEntity createClient(String name, String carCode) {
        ClientEntity client = new ClientEntity();
        client.setFullName(name);
        client.setCarCode(carCode);
        return clientDao.save(client);
    }

    //Метод обновляет данные о клиенте (имя и номера машины)
    @Override
    @Transactional
    public ClientEntity updateClient(Long id, ClientEntity clientEntity) {
        Optional<ClientEntity> client = clientDao.findById(id);
        if (!client.isPresent()) {
            throw new NotFoundException();
        }
        client.get().setFullName(clientEntity.getFullName());
        client.get().setCarCode(clientEntity.getCarCode());
        return clientDao.save(client.get());
    }

    //Метод удаляет клиента
    @Override
    public void deleteById(Long id) {
        clientDao.deleteById(id);
    }

    //Метод ищет первое свободное парковочное место и занимет его
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

    //Метод особождает парковочное место
    @Override
    @Transactional
    public ClientEntity freeParkingSpot(Long id){
        Optional<ClientEntity> client = clientDao.findById(id);
        if (!client.isPresent()) {
            throw new NotFoundException();
        }
        ParkingSpotEntity parkingSpotEntity = client.get().getParkingSpotEntity();
        parkingSpotEntity.setEmpty(true);
        parkingSpotService.updateParkingSpot(parkingSpotEntity.getId(), parkingSpotEntity);
        client.get().setParkingSpotEntity(null);
        client.get().setParkingRateEntity(null);
        return clientDao.save(client.get());
    }

    //Метод для удаления всех связей одним запросом
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
