package com.sigma.task.sigmatesttask.entities;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//Описание сущности Парковочного тарифа (Цена и время бронирования)

import javax.persistence.*;

@Entity
@Table(name = "rate_table")
public class ParkingRateEntity {
    @Id
    @Column(name = "id_parking_rate")
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Getter
    private Long id;

    @Column(name = "rate_price")
    @Getter
    @Setter
    private int pricePerMinute;

    @Column(name = "min_time")
    @Getter
    @Setter
    private int minTime;



    public ParkingRateEntity(){}
}
