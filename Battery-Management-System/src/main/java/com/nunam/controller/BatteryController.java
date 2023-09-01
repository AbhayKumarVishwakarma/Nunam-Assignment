package com.nunam.controller;

import com.nunam.model.Battery;
import com.nunam.model.BatteryDetails;
import com.nunam.model.dto.BatteryCurrent;
import com.nunam.model.dto.BatteryTemperature;
import com.nunam.model.dto.BatteryVoltage;
import com.nunam.service.BatteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/battery")
public class BatteryController {

    @Autowired
    private BatteryService batteryService;

    @PostMapping("/add")
    public ResponseEntity<String> addBatteryHandler(@RequestBody Battery battery, @RequestParam Integer vehicleId){
        String msg = batteryService.addBattery(battery, vehicleId);
        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    @DeleteMapping("/{batteryId}")
    public ResponseEntity<String> deleteBatteryHandler(@PathVariable Integer batteryId){
        String msg = batteryService.deleteBattery(batteryId);
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }

    @PostMapping("/{batteryId}/save-all-data")
    public ResponseEntity<String> getBatteryDataInRange(@PathVariable Integer batteryId) {

        // Implement logic to retrieve and return battery data within the specified time range
        String saveJsonBatteryDetailData = batteryService.saveJsonBatteryDetailData(batteryId);

        return new ResponseEntity<>(saveJsonBatteryDetailData, HttpStatus.OK);
    }


    @PostMapping("/{batteryId}/save-details")
    public ResponseEntity<BatteryDetails> saveBatteryDetailsHandler(@RequestBody BatteryDetails details, @PathVariable Integer batteryId){
        BatteryDetails savedDetail = batteryService.saveBatteryDetail(details, batteryId);
        return new ResponseEntity<>(savedDetail, HttpStatus.OK);
    }

    @GetMapping("/{batteryId}/all-details")
    public ResponseEntity<List<BatteryDetails>> getAllBatteryDetailsHandler(@PathVariable Integer batteryId){
        List<BatteryDetails> list = batteryService.getAllBatteryDetails(batteryId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{batteryId}/voltage")
    public ResponseEntity<List<BatteryVoltage>> getBatteryVoltageHandler(@PathVariable Integer batteryId){
        List<BatteryVoltage> list = batteryService.getBatteryVoltage(batteryId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{batteryId}/temperature")
    public ResponseEntity<List<BatteryTemperature>> getBatteryTemperatureHandler(@PathVariable Integer batteryId){
        List<BatteryTemperature> list = batteryService.getBatteryTemperature(batteryId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{batteryId}/current")
    public ResponseEntity<List<BatteryCurrent>> getBatteryCurrentHandler(@PathVariable Integer batteryId){
        List<BatteryCurrent> list = batteryService.getBatteryCurrent(batteryId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{batteryId}/time-range/{startTime}/{endTime}")
    public ResponseEntity<List<BatteryDetails>> getBatteryDataInRangeHandler(
            @PathVariable Integer batteryId,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startTime,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime) {

        List<BatteryDetails> batteryDataInRange = batteryService.getBatteryDataInRange(batteryId, startTime, endTime);

        return new ResponseEntity<>(batteryDataInRange, HttpStatus.OK);
    }

}
