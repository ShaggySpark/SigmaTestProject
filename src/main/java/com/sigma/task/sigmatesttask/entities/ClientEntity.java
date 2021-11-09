package com.sigma.task.sigmatesttask.entities;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "client_table")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id_client")
    @Getter
    private Long id;

    @Column(name = "full_name")
    @Getter
    @Setter
    private String fullName;

    @Column(name = "car_code")
    @Getter
    @Setter
    private String carCode;

    @OneToOne(optional = false)
    @JoinColumn(name="id_parking_spot")
    @Getter
    @Setter
    private ParkingSpotEntity parkingSpotEntity;

    @OneToOne(optional = false)
    @JoinColumn(name="id_parking_rate")
    @Getter
    @Setter
    private ParkingRateEntity parkingRateEntity;

    public ClientEntity(){}
}
