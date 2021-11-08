package com.ss.scrumptious_drivers.mapper;

import com.ss.scrumptious.common_entities.entity.Delivery;
import com.ss.scrumptious_drivers.dto.CreateDeliveryDto;
import com.ss.scrumptious_drivers.dto.UpdateDeliveryDto;

public class DeliveryDtoMapper {
 
    public static Delivery map(CreateDeliveryDto dto) {
        // setting required values
        Delivery delivery = Delivery.builder()
            .build();

        // setting values if they exist in the json
        if (dto.getEstimatedDeliveryTime() != null) {
            delivery.setEstimatedDeliveryTime(dto.getEstimatedDeliveryTime());
        }
        if (dto.getActualDeliveryTime() != null) {
            delivery.setActualDeliveryTime(dto.getActualDeliveryTime());
        }
        if (dto.getDeliveryStatus() != null) {
            delivery.setDeliveryStatus(dto.getDeliveryStatus());
        }
        if (dto.getDriverCompensation() != null) {
            delivery.setDriverCompensation(dto.getDriverCompensation());
        }
        if (dto.getPickedUpAt() != null) {
            delivery.setPickedUpAt(dto.getPickedUpAt());
        }
        return delivery;
    }

    public static Delivery map(Delivery delivery, UpdateDeliveryDto dto) {
        if (dto.getEstimatedDeliveryTime() != null) {
            delivery.setEstimatedDeliveryTime(dto.getEstimatedDeliveryTime());
        }
        if (dto.getActualDeliveryTime() != null) {
            delivery.setActualDeliveryTime(dto.getActualDeliveryTime());
        }
        if (dto.getDeliveryStatus() != null) {
            delivery.setDeliveryStatus(dto.getDeliveryStatus());
        }
        if (dto.getDriverCompensation() != null) {
            delivery.setDriverCompensation(dto.getDriverCompensation());
        }
        if (dto.getPickedUpAt() != null) {
            delivery.setPickedUpAt(dto.getPickedUpAt());
        }
        return delivery;
    }
}
