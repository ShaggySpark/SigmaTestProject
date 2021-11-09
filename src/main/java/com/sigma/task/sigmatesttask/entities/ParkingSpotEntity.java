package com.sigma.task.sigmatesttask.entities;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "parking_spot_table")
public class ParkingSpotEntity {
    @Id
    @Column(name = "id_parking_spot")
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Getter
    private Long id;

    @Column(name = "parking_code")
    @Getter
    @Setter
    private String code; // A21

    @Column(name = "is_empty")
    @Getter
    @Setter
    private boolean isEmpty = true;


    public ParkingSpotEntity(){ }


}
