package com.ss.scrumptious_drivers.security;

import java.util.UUID;

import com.ss.scrumptious_drivers.dao.DeliveryRepository;
import com.ss.scrumptious_drivers.dto.CreateDeliveryDto;
import com.ss.scrumptious_drivers.dto.UpdateDeliveryDto;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DriverAuthenticationManager {

  private final DeliveryRepository deliveryRepository;

  public boolean driverIdMatches(Authentication authentication, UUID driverId) {
    try {
        JwtPrincipalModel principal = (JwtPrincipalModel) authentication.getPrincipal();
        return principal.getUserId().equals(driverId);
    } catch (ClassCastException ex) {
        return false;
    }
  }

  public boolean driverIdMatches(Authentication authentication, Long deliveryId) {
    try {
        JwtPrincipalModel principal = (JwtPrincipalModel) authentication.getPrincipal();
        return deliveryRepository.findById(deliveryId).map(d -> d.getDriver().getId().equals(principal.getUserId())).orElse(false);
    } catch (ClassCastException ex) {
        return false;
    }
  }

  public boolean driverIdMatches(Authentication authentication, CreateDeliveryDto createDeliveryDto) {
    try {
        JwtPrincipalModel principal = (JwtPrincipalModel) authentication.getPrincipal();
        return principal.getUserId().equals(createDeliveryDto.getDriverId());
      } catch (ClassCastException ex) {
        return false;
      }
  }

  public boolean driverIdMatches(Authentication authentication, UpdateDeliveryDto updateDeliveryDto) {
    try {
        JwtPrincipalModel principal = (JwtPrincipalModel) authentication.getPrincipal();
        return principal.getUserId().equals(updateDeliveryDto.getDriverId());
      } catch (ClassCastException ex) {
        return false;
      }
  }

  public boolean deliveryIsOpen(Long deliveryId) {
    try {
        return deliveryRepository.findById(deliveryId).map(d -> d.getDeliveryStatus().equals("UNASSIGNED")).orElse(false);
      } catch (ClassCastException ex) {
        return false;
      }
  }
}