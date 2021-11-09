package com.sigma.task.sigmatesttask.controllers;

import com.sigma.task.sigmatesttask.entities.ParkingSpotEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sigma.task.sigmatesttask.services.ParkingSpotService;

import java.util.List;

@RestController
@RequestMapping("/parkingspots")
public class ParkingSpotApi {
    @Autowired
    ParkingSpotService parkingSpotService;

    @GetMapping
    public ResponseEntity<?> getParkingSpots() {
        List<ParkingSpotEntity> entities = parkingSpotService.listParkingSpots();
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getParkingSpot(@PathVariable Long id) {
        ParkingSpotEntity entity = parkingSpotService.findById(id);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createParkingSpot(@PathVariable ParkingSpotEntity parkingSpotEntity) {
        parkingSpotService.createParkingSpot(parkingSpotEntity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateParkingSpot(@PathVariable Long id, @RequestBody ParkingSpotEntity parkingSpotEntity){
        parkingSpotService.updateParkingSpot(id, parkingSpotEntity);
        return new ResponseEntity<>(parkingSpotEntity, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteParkingSpot(@PathVariable Long id){
        parkingSpotService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
