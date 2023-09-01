package com.nunam.service;

import com.nunam.exception.VehicleException;
import com.nunam.model.Vehicle;

public interface VehicleService {
    public Vehicle addVehicle(Vehicle vehicle) throws VehicleException;
}
