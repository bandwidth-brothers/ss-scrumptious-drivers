package com.ss.scrumptious_drivers.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import com.ss.scrumptious.common_entities.entity.Driver;
import com.ss.scrumptious_drivers.dto.CreateDriverDto;
import com.ss.scrumptious_drivers.dto.UpdateDriverDto;
import com.ss.scrumptious_drivers.service.DriverService;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<List<Driver>> getAllDrivers() {
        log.info("GET Driver all");
        List<Driver> drivers = driverService.getAllDrivers();
        if (drivers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(drivers);
    }

    @PreAuthorize("hasRole('ADMIN')"
    + " OR @driverAuthenticationManager.driverIdMatches(authentication, #driverId)")
    @GetMapping(value = "/{driverId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Driver> getDriverById(@PathVariable UUID driverId) {
        log.info("Get Driver id = " + driverId);
        return ResponseEntity.of(Optional.ofNullable(driverService.getDriverById(driverId)));
    }

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Void> createNewDriver(@Valid @RequestBody CreateDriverDto createDriverDto) {
        log.info("POST Driver");
        Driver driver = driverService.createNewDriver(createDriverDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{driverId}")
            .buildAndExpand(driver.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PreAuthorize("hasRole('ADMIN')"
    + " OR @driverAuthenticationManager.driverIdMatches(authentication, #driverId)")
    @PutMapping(value = "/{driverId}", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Void> updateExistingDriver(@Valid @RequestBody UpdateDriverDto updateDriverDto, @PathVariable UUID driverId) {
        log.info("PUT Driver id = " + driverId);
        driverService.updateDriver(driverId, updateDriverDto);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')"
    + " OR @driverAuthenticationManager.driverIdMatches(authentication, #driverId)")
    @DeleteMapping(value = "/{driverId}")
    public ResponseEntity<Void> deleteDriver(@PathVariable UUID driverId) {
        log.info("DELETE Driver id = " + driverId);
        driverService.deleteDriver(driverId);
        return ResponseEntity.noContent().build();
    }

}