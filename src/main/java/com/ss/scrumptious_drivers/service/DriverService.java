package com.ss.scrumptious_drivers.service;

import java.util.List;
import java.util.UUID;

import com.ss.scrumptious.common_entities.entity.Driver;
import com.ss.scrumptious_drivers.dto.CreateDriverDto;
import com.ss.scrumptious_drivers.dto.UpdateDriverDto;


public interface DriverService {

    List<Driver> getAllDrivers();

    Driver getDriverById(UUID driverId);

    Driver createNewDriver(CreateDriverDto createDriverDto);

    Driver updateDriver(UUID driverId, UpdateDriverDto updateDriverDto);

    void deleteDriver(UUID driverId);

}
