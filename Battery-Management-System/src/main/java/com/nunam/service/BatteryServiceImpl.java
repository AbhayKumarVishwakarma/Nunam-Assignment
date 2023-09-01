package com.nunam.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nunam.exception.BatteryException;
import com.nunam.exception.VehicleException;
import com.nunam.model.Battery;
import com.nunam.model.BatteryDetails;
import com.nunam.model.Vehicle;
import com.nunam.model.dto.BatteryCurrent;
import com.nunam.model.dto.BatteryTemperature;
import com.nunam.model.dto.BatteryVoltage;
import com.nunam.repository.BatteryDetailsRepository;
import com.nunam.repository.BatteryRepository;
import com.nunam.repository.VehicleRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class BatteryServiceImpl implements BatteryService{

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private BatteryRepository batteryRepository;

    @Autowired
    private BatteryDetailsRepository detailsRepository;

    @Override
    public String addBattery(Battery battery, Integer vehicleId) throws BatteryException, VehicleException {
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new VehicleException("Not find any vehicle with id: " + vehicleId));
        if(battery == null) throw new BatteryException("Invalid battery!");

        vehicle.getBatteryList().add(battery);
        battery.setVehicle(vehicle);

        batteryRepository.save(battery);
        return "New battery added successfully!";
    }

    @Override
    public String deleteBattery(Integer batteryId) throws BatteryException {
        Battery battery = batteryRepository.findById(batteryId).orElseThrow(() -> new BatteryException("Not find battery with id: " + batteryId));
        Vehicle vehicle = vehicleRepository.findById(battery.getVehicle().getVehicleId()).get();

        vehicle.getBatteryList().remove(battery);
        batteryRepository.delete(battery);

        return "Battery deleted successfully!";
    }

    @Override
    public String saveJsonBatteryDetailData(Integer batteryId) throws BatteryException {

        // Load the JSON data from the classpath resource
        ClassPathResource resource = new ClassPathResource("data.json");

        // Initialize an ObjectMapper instance for JSON serialization and deserialization
        ObjectMapper objectMapper = new ObjectMapper();

        // Register the JavaTimeModule to handle Java 8 date and time types during JSON processing
        objectMapper.registerModule(new JavaTimeModule());

        List<BatteryDetails> detailsList = new ArrayList<>();

        try {
            // Read the content of the JSON file into a String
            String staticDataString = IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);

            // Parse the JSON data into a Map<String, Object>
            Map<String, Object> map = objectMapper.readValue(staticDataString, new TypeReference<Map<String, Object>>() {});

            // Extract the "details" list from the map and convert it into a List<BatteryDetails>
            detailsList = objectMapper.convertValue(map.get("details"), new TypeReference<List<BatteryDetails>>() {});

        } catch (IOException ex) {
            throw new BatteryException("Error in reading the candle data.", ex);
        }

        // If no details are found in the JSON data, throw an exception
        if(detailsList.isEmpty()) throw new BatteryException("Not found any battery details for adding to database!");

        Battery battery = batteryRepository.findById(batteryId).orElseThrow(() -> new BatteryException("Not find battery with id: " + batteryId));

        detailsList.forEach(batteryDetails -> {
            batteryDetails.setBattery(battery);
        });

        detailsRepository.saveAll(detailsList);

        return "Json battery details added successfully!";
    }

    @Override
    public BatteryDetails saveBatteryDetail(BatteryDetails batteryDetails, Integer batteryId) throws BatteryException {
        Battery battery = batteryRepository.findById(batteryId).orElseThrow(() -> new BatteryException("Not find battery with id: " + batteryId));
        if(batteryDetails == null) throw new BatteryException("Invalid battery details!");

        battery.getDetailsList().add(batteryDetails);
        batteryDetails.setBattery(battery);

        return detailsRepository.save(batteryDetails);
    }

    @Override
    public List<BatteryDetails> getAllBatteryDetails(Integer batteryId) throws BatteryException {
        List<BatteryDetails> list = detailsRepository.findAllByBatteryBatteryId(batteryId);
        if(list.isEmpty()) throw new BatteryException("Not find any battery details!");
        return list;
    }

    @Override
    public List<BatteryVoltage> getBatteryVoltage(Integer batteryId) throws BatteryException {
        List<BatteryVoltage> list = detailsRepository.findVoltageByBatteryId(batteryId);
        if(list.isEmpty()) throw new BatteryException("Not find any battery details!");
        return list;
    }

    @Override
    public List<BatteryTemperature> getBatteryTemperature(Integer batteryId) throws BatteryException {
        List<BatteryTemperature> list = detailsRepository.findTemperatureByBatteryId(batteryId);
        if(list.isEmpty()) throw new BatteryException("Not find any battery details!");
        return list;
    }

    @Override
    public List<BatteryCurrent> getBatteryCurrent(Integer batteryId) throws BatteryException {
        List<BatteryCurrent> list = detailsRepository.findCurrentByBatteryId(batteryId);
        if(list.isEmpty()) throw new BatteryException("Not find any battery details!");
        return list;
    }

    @Override
    public List<BatteryDetails> getBatteryDataInRange(Integer batteryId, LocalDateTime startTime, LocalDateTime endTime) throws BatteryException {
        List<BatteryDetails> batteryDetailsList = detailsRepository.findAllByBatteryBatteryIdAndTimeBetween( batteryId, startTime, endTime);
        if(batteryDetailsList.isEmpty()) throw new BatteryException("Not find any battery details between the time!");
        return batteryDetailsList;
    }
}
