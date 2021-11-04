package com.ss.scrumptious_drivers.service;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import com.ss.scrumptious.common_entities.entity.Address;
import com.ss.scrumptious.common_entities.entity.Driver;
import com.ss.scrumptious_drivers.client.AuthClient;
import com.ss.scrumptious_drivers.dao.AddressRepository;
import com.ss.scrumptious_drivers.dao.DriverRepository;
import com.ss.scrumptious_drivers.dto.AuthDto;
import com.ss.scrumptious_drivers.dto.CreateDriverDto;
import com.ss.scrumptious_drivers.dto.UpdateDriverDto;
import com.ss.scrumptious_drivers.exception.NoSuchDriverException;
import com.ss.scrumptious_drivers.mapper.AddressDtoMapper;
import com.ss.scrumptious_drivers.mapper.DriverDtoMapper;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final AuthClient authClient;
    private final DriverRepository driverRepository;
    private final AddressRepository addressRepository;

    @Override
    public List<Driver> getAllDrivers() {
        log.trace("getAllDrivers");
        return driverRepository.findAll();
    }

    @Transactional
    @Override
    public Driver createNewDriver(CreateDriverDto createDriverDto) {
        log.trace("createNewDriver");
        AuthDto authDto = AuthDto.builder().email(createDriverDto.getEmail())
            .password(createDriverDto.getPassword()).build();
        ResponseEntity<UUID> resp = authClient.createNewAccountDriver(authDto);
        if (resp.getBody() == null){
            throw new IllegalStateException("Email is already in use");
        }

        Driver driver = DriverDtoMapper.map(resp.getBody(), createDriverDto);

        Address address = addressRepository.saveAndFlush(AddressDtoMapper.map(createDriverDto.getAddress()));
        driver.setAddress(address);

        return driverRepository.saveAndFlush(driver);
    }

    @Override
    public Driver getDriverById(UUID driverId) {
        log.trace("getDriverById driverId = " + driverId);
        return driverRepository.findById(driverId)
            .orElseThrow(() -> new NoSuchDriverException(driverId));
    }


    @Transactional
    @Override
    public Driver updateDriver(UUID driverId, UpdateDriverDto updateDriverDto) {
        log.trace("updateDriver driverId = " + driverId);
        Driver driver = getDriverById(driverId);

        driver = DriverDtoMapper.map(driver, updateDriverDto);

        if (updateDriverDto.getAddress() != null) {
            Address address = addressRepository.saveAndFlush(AddressDtoMapper.map(driver.getAddress(), updateDriverDto.getAddress()));
            driver.setAddress(address);
        }

        return driverRepository.saveAndFlush(driver);
    }

    @Override
    public void deleteDriver(UUID driverId) {
        log.trace("deleteDriver driverId = " + driverId);

        driverRepository.findById(driverId).ifPresent(driverRepository::delete);
    }

}
