package com.nunam.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class BatteryTemperature {
    private Integer batteryId;
    private Integer detailsId;
    private double temperature;
}
