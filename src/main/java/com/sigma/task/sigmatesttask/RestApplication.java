package com.sigma.task.sigmatesttask;

import com.sigma.task.sigmatesttask.controllers.ParkingSpotApi;
import com.sigma.task.sigmatesttask.entities.ParkingRateEntity;
import com.sigma.task.sigmatesttask.entities.ParkingSpotEntity;
import com.sigma.task.sigmatesttask.services.ParkingRateService;
import com.sigma.task.sigmatesttask.services.ParkingSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.persistence.Access;
import java.util.Arrays;


@SpringBootApplication
public class RestApplication {
@Autowired
private static ParkingRateService parkingRateService;
@Autowired
private static ParkingSpotService parkingSpotService;

    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);

        ParkingSpotEntity parking1 = new ParkingSpotEntity();
        parking1.setCode("AA01");
        parkingSpotService.createParkingSpot(parking1);
        ParkingSpotEntity parking2 = new ParkingSpotEntity();
        parking2.setCode("AA02");
        parkingSpotService.createParkingSpot(parking2);
        ParkingSpotEntity parking3 = new ParkingSpotEntity();
        parking3.setCode("AA03");
        parkingSpotService.createParkingSpot(parking3);
        ParkingSpotEntity parking4 = new ParkingSpotEntity();
        parking4.setCode("AA04");
        parkingSpotService.createParkingSpot(parking4);
        ParkingSpotEntity parking5 = new ParkingSpotEntity();
        parking5.setCode("AA05");
        parkingSpotService.createParkingSpot(parking5);

        ParkingRateEntity rate1 = new ParkingRateEntity();
        rate1.setMinTime(1);
        rate1.setPricePerMinute(5);
        parkingRateService.createParkingRate(rate1);
        ParkingRateEntity rate2 = new ParkingRateEntity();
        rate2.setMinTime(60);
        rate2.setPricePerMinute(4);
        parkingRateService.createParkingRate(rate2);
        ParkingRateEntity rate3 = new ParkingRateEntity();
        rate3.setMinTime(480);
        rate3.setPricePerMinute(2);
        parkingRateService.createParkingRate(rate3);

    }

}