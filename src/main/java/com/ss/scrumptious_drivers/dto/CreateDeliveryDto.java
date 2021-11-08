package com.ss.scrumptious_drivers.dto;

import java.time.ZonedDateTime;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateDeliveryDto {

	@NotNull
    private Long addressId;

    @NotNull
    private UUID driverId;

    @NotNull
    private Long orderId;

    @Builder.Default
    private ZonedDateTime estimatedDeliveryTime = ZonedDateTime.now().plusHours(1);

    private String deliveryStatus;

    private ZonedDateTime actualDeliveryTime;

    private Float driverCompensation;

    private ZonedDateTime pickedUpAt;
    
}