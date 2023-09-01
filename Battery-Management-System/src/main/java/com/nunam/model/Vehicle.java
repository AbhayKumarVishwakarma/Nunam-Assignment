package com.nunam.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vehicleId;

    private String name;
    private String modelNo;
    private Integer manufactureYear;

    @JsonIgnore
    @OneToMany(mappedBy = "vehicle")
    private List<Battery> batteryList;
}
