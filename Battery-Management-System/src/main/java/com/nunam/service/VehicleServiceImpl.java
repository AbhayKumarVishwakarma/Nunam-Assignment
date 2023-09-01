package com.nunam.service;

import com.nunam.exception.VehicleException;
import com.nunam.model.Vehicle;
import com.nunam.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService{

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public Vehicle addVehicle(Vehicle vehicle) throws VehicleException {
        if(vehicle == null) throw new VehicleException("Invalid vehicle!");
        return vehicleRepository.save(vehicle);
    }

}
