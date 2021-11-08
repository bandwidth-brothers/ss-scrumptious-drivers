package com.ss.scrumptious_drivers.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import com.ss.scrumptious.common_entities.entity.Delivery;
import com.ss.scrumptious_drivers.dto.CreateDeliveryDto;
import com.ss.scrumptious_drivers.dto.UpdateDeliveryDto;
import com.ss.scrumptious_drivers.service.DeliveryService;

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
@RequestMapping("/deliveries")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<List<Delivery>> getAllDeliveries() {
        log.info("GET Delivery all");
        List<Delivery> deliveries = deliveryService.getAllDeliveries();
        if (deliveries.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(deliveries);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DRIVER')")
    @GetMapping(value = "/open", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<List<Delivery>> getOpenDeliveries() {
        log.info("GET Open Delivery");
        List<Delivery> deliveries = deliveryService.getAllOpenDeliveries();
        if (deliveries.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(deliveries);
    }

    @PreAuthorize("hasRole('ADMIN')"
    + " OR @driverAuthenticationManager.driverIdMatches(authentication, #deliveryId)")
    @GetMapping(value = "/{deliveryId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Delivery> getById(@PathVariable Long deliveryId) {
        log.info("Get Delivery id = " + deliveryId);
        return ResponseEntity.of(Optional.ofNullable(deliveryService.getDeliveryById(deliveryId)));
    }

    @PreAuthorize("hasRole('ADMIN')"
    + " OR @driverAuthenticationManager.driverIdMatches(authentication, #createDeliveryDto)")
    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Void> createNewDelivery(@Valid @RequestBody CreateDeliveryDto createDeliveryDto) {
        log.info("POST Delivery");
        Delivery delivery = deliveryService.createNewDelivery(createDeliveryDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{deliveryId}")
            .buildAndExpand(delivery.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PreAuthorize("hasRole('ADMIN')"
    + " OR @driverAuthenticationManager.driverIdMatches(authentication, #updateDeliveryDto)")
    @PutMapping(value = "/{deliveryId}", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ResponseEntity<Void> updateExistingDelivery(@Valid @RequestBody UpdateDeliveryDto updateDeliveryDto, @PathVariable Long deliveryId) {
        log.info("PUT Delivery id = " + deliveryId);
        deliveryService.updateDelivery(deliveryId, updateDeliveryDto);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')"
    + " OR @driverAuthenticationManager.driverIdMatches(authentication, #deliveryId)")
    @DeleteMapping(value = "/{deliveryId}")
    public ResponseEntity<Void> deleteDelivery(@PathVariable Long deliveryId) {
        log.info("DELETE Delivery id = " + deliveryId);
        deliveryService.deleteDelivery(deliveryId);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')"
    + " OR (@driverAuthenticationManager.driverIdMatches(authentication, #driverId)"
    + " AND @driverAuthenticationManager.deliveryIsOpen(#deliveryId))")
    @PutMapping(value = "/{deliveryId}/drivers/{driverId}")
    public ResponseEntity<Void> updateDeliveryWithDriver(@PathVariable Long deliveryId, @PathVariable UUID driverId) {
        log.info("PUT Delivery id = " + deliveryId + ", driverId = " + driverId);
        deliveryService.updateDeliveryWithDriver(deliveryId, driverId);
        return ResponseEntity.noContent().build();
    }
}