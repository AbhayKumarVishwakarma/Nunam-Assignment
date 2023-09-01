package com.nunam.controller;

import com.nunam.model.Vehicle;
import com.nunam.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/add")
    public ResponseEntity<Vehicle> addVehicleHandler(@RequestBody Vehicle vehicle){
        Vehicle addedVehicle = vehicleService.addVehicle(vehicle);
        return new ResponseEntity<>(addedVehicle, HttpStatus.CREATED);
    }

}
