package com.ss.scrumptious_drivers.security;

import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class DriverAuthenticationManager {

  public boolean driverIdMatches(Authentication authentication, UUID id) {
    try {
        JwtPrincipalModel principal = (JwtPrincipalModel) authentication.getPrincipal();
        return principal.getUserId().equals(id);
    } catch (ClassCastException ex) {
        return false;
    }
  }
}