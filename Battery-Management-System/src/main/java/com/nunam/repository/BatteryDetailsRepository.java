package com.nunam.repository;

import com.nunam.model.BatteryDetails;
import com.nunam.model.dto.BatteryCurrent;
import com.nunam.model.dto.BatteryTemperature;
import com.nunam.model.dto.BatteryVoltage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BatteryDetailsRepository  extends JpaRepository<BatteryDetails, Integer> {
    List<BatteryDetails> findAllByBatteryBatteryId(Integer batteryId);

    @Query("SELECT new com.nunam.model.dto.BatteryVoltage(d.battery.batteryId, d.detailsId, d.voltage) FROM BatteryDetails d WHERE d.battery.batteryId = ?1")
    List<BatteryVoltage> findVoltageByBatteryId(Integer batteryId);

    @Query("SELECT new com.nunam.model.dto.BatteryCurrent(d.battery.batteryId, d.detailsId, d.current) FROM BatteryDetails d WHERE d.battery.batteryId = ?1")
    List<BatteryCurrent> findCurrentByBatteryId(Integer batteryId);

    @Query("SELECT new com.nunam.model.dto.BatteryTemperature(d.battery.batteryId, d.detailsId, d.temperature) FROM BatteryDetails d WHERE d.battery.batteryId = ?1")
    List<BatteryTemperature> findTemperatureByBatteryId(Integer batteryId);

    List<BatteryDetails> findAllByBatteryBatteryIdAndTimeBetween(Integer batteryId, LocalDateTime startTime, LocalDateTime endTime);

}
