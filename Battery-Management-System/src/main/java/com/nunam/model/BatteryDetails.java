package com.nunam.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
public class BatteryDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer detailsId;

    @Min(value = 0, message = "Current cannot be negative")
    private Integer current;

    @Min(value = 0, message = "Voltage cannot be negative")
    private double voltage;

    private double temperature;
    private LocalDateTime time;

    @JsonIgnore
    @ManyToOne
    private Battery battery;
}
