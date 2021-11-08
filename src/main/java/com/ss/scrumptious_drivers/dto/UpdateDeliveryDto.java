package com.ss.scrumptious_drivers.dto;

import java.time.ZonedDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateDeliveryDto {

    private Long addressId;

    private UUID driverId;

    private Long orderId;

    private ZonedDateTime estimatedDeliveryTime;

    private String deliveryStatus;

    private ZonedDateTime actualDeliveryTime;

    private Float driverCompensation;

    private ZonedDateTime pickedUpAt;
    
}