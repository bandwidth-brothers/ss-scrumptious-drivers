package com.ss.scrumptious_drivers.mapper;

import java.util.UUID;

import com.ss.scrumptious.common_entities.entity.Driver;
import com.ss.scrumptious_drivers.dto.CreateDriverDto;
import com.ss.scrumptious_drivers.dto.UpdateDriverDto;

public class DriverDtoMapper {
    
    public static Driver map(UUID driverId, CreateDriverDto dto) {
        // setting required values
        Driver driver = Driver.builder()
            .id(driverId)
            .firstName(dto.getFirstName())
            .lastName(dto.getLastName())
            .phone(dto.getPhone())
            .licenseNumber(dto.getLicenseNum())
            .build();

        // setting values if they exist in the json
        if (dto.getDob() != null) {
            driver.setDob(dto.getDob());
        }
        if (dto.getRating() != null) {
            driver.setRating(dto.getRating());
        }
        if (dto.getPicture() != null) {
            driver.setPicture(dto.getPicture());
        }
        if (dto.getStatus() != null) {
            driver.setStatus(dto.getStatus());
        }
        if (dto.getEmail() != null) {
            driver.setEmail(dto.getEmail());
        }
        return driver;
    }

    public static Driver map(Driver driver, UpdateDriverDto dto) {
        if (dto.getFirstName() != null) {
            driver.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName() != null) {
            driver.setLastName(dto.getLastName());
        }
        if (dto.getPhone() != null) {
            driver.setPhone(dto.getPhone());
        }
        if (dto.getDob() != null) {
            driver.setDob(dto.getDob());
        }
        if (dto.getLicenseNum() != null) {
            driver.setLicenseNumber(dto.getLicenseNum());
        }
        if (dto.getRating() != null) {
            driver.setRating(dto.getRating());
        }
        if (dto.getPicture() != null) {
            driver.setPicture(dto.getPicture());
        }
        if (dto.getStatus() != null) {
            driver.setStatus(dto.getStatus());
        }
        if (dto.getEmail() != null) {
            driver.setEmail(dto.getEmail());
        }
        return driver;
    }
}
