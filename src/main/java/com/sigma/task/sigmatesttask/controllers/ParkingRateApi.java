package com.sigma.task.sigmatesttask.controllers;

import com.sigma.task.sigmatesttask.entities.ParkingRateEntity;
import com.sigma.task.sigmatesttask.services.ParkingRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parkingrates")
public class ParkingRateApi {
    @Autowired
    ParkingRateService parkingRateService;

    @GetMapping
    public ResponseEntity<?> getParkingRates() {
        List<ParkingRateEntity> entities = parkingRateService.listParkingRates();
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getParkingRate(@PathVariable Long id) {
        ParkingRateEntity entity = parkingRateService.findById(id);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createParkingRate(@PathVariable ParkingRateEntity parkingRateEntity) {
        parkingRateService.createParkingRate(parkingRateEntity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateParkingRate(@PathVariable Long id, @RequestBody ParkingRateEntity parkingRateEntity){
        parkingRateService.updateParkingRate(id, parkingRateEntity);
        return new ResponseEntity<>(parkingRateEntity, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteParkingRate(@PathVariable Long id){
        parkingRateService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
