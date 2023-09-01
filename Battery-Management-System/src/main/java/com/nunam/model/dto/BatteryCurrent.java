package com.nunam.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BatteryCurrent {
    private Integer batteryId;
    private Integer detailsId;
    private Integer current;
}
