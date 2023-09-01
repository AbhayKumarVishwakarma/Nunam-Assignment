package com.nunam.service;

import com.nunam.exception.BatteryException;
import com.nunam.exception.VehicleException;
import com.nunam.model.Battery;
import com.nunam.model.BatteryDetails;
import com.nunam.model.dto.BatteryCurrent;
import com.nunam.model.dto.BatteryTemperature;
import com.nunam.model.dto.BatteryVoltage;

import java.time.LocalDateTime;
import java.util.List;

public interface BatteryService {

    /**
     * This method is use for adding battery to vehicle
     * @param battery
     * @param vehicleId
     * @return String message
     * @throws BatteryException if provided battery is null
     * @throws VehicleException if not found Vehicle based on vehicleId
     */
    public String addBattery(Battery battery, Integer vehicleId) throws BatteryException, VehicleException;

    /**
     * This method is used for removing the battery from vehicle
     * @param batteryId
     * @return String message
     * @throws BatteryException if not found battery from database based on batteryId
     */
    public String deleteBattery(Integer batteryId) throws BatteryException;

    /**
     * This method is used to read battery details data from a JSON file, convert it into list of BatteryDetails, and add the data to the database
     * @param batteryId In which battery you want to add the details
     * @return A success message
     * @throws BatteryException if noting present in data.json, or if not find any battery details in detail list
     */
    public String saveJsonBatteryDetailData(Integer batteryId) throws BatteryException;

    /**
     * This method is used for saving the battery details like (current, temperature, voltage, time)
     * @param batteryDetails
     * @param batteryId
     * @return Saved battery details
     * @throws BatteryException if not found battery from database based on batteryId or if provided battery details is null
     */
    public BatteryDetails saveBatteryDetail(BatteryDetails batteryDetails, Integer batteryId) throws BatteryException;

    /**
     * This method is used for getting the all battery details from database based on batteryId
     * @param batteryId
     * @return list of battery details
     * @throws BatteryException if not found any battery details
     */
    public List<BatteryDetails> getAllBatteryDetails(Integer batteryId) throws BatteryException;

    /**
     * This method is used for getting the voltage of battery
     * @param batteryId
     * @return list of battery voltage
     * @throws BatteryException if not found any battery details
     */
    public List<BatteryVoltage> getBatteryVoltage(Integer batteryId) throws BatteryException;

    /**
     * This method is used for getting the temperature of battery
     * @param batteryId
     * @return list of battery temperature
     * @throws BatteryException if not found any battery details
     */
    public List<BatteryTemperature> getBatteryTemperature(Integer batteryId) throws BatteryException;

    /**
     * This method is used for getting the current of battery
     * @param batteryId
     * @return list of battery current
     * @throws BatteryException if not found any battery details
     */
    public List<BatteryCurrent> getBatteryCurrent(Integer batteryId) throws BatteryException;

    /**
     * This method is used for getting the battery details between the provided time range
     * @param batteryId
     * @param startTime
     * @param endTime
     * @return list of battery details
     * @throws BatteryException if not found any battery details between the time range
     */
    public List<BatteryDetails> getBatteryDataInRange(Integer batteryId, LocalDateTime startTime, LocalDateTime endTime) throws BatteryException;

}
