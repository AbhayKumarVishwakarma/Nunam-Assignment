package com.nunam.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Battery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer batteryId;

    private Integer capacity;
    private String type;
    private Integer warranty;

    @ManyToOne
    private Vehicle vehicle;

    @JsonIgnore
    @OneToMany(mappedBy = "battery")
    private List<BatteryDetails> detailsList;
}
