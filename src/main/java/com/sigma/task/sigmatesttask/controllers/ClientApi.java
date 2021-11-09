package com.sigma.task.sigmatesttask.controllers;

import com.sigma.task.sigmatesttask.entities.ClientEntity;
import com.sigma.task.sigmatesttask.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientApi {
    @Autowired
    ClientService clientService;

    @GetMapping
    public ResponseEntity<?> getClients() {
        List<ClientEntity> entities = clientService.clientsList();
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClient(@PathVariable Long id) {
        ClientEntity entity = clientService.findById(id);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createClient(@PathVariable ClientEntity clientEntity) {
        clientService.createClient(clientEntity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateClient(@PathVariable Long id, @RequestBody ClientEntity clientEntity){
        clientService.updateClient(id, clientEntity);
        return new ResponseEntity<>(clientEntity, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id){
        clientService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/clearallorders")
    public ResponseEntity<?> clearAllOrders(){
        clientService.clearAllOrders();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
