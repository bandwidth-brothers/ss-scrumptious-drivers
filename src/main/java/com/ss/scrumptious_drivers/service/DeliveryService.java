package com.ss.scrumptious_drivers.service;

import java.util.List;
import java.util.UUID;

import com.ss.scrumptious.common_entities.entity.Delivery;
import com.ss.scrumptious_drivers.dto.CreateDeliveryDto;
import com.ss.scrumptious_drivers.dto.UpdateDeliveryDto;

public interface DeliveryService {

    public List<Delivery> getAllDeliveries();

    public List<Delivery> getDeliveriesByDriverId(UUID driverId);

    public List<Delivery> getAllOpenDeliveries();

    public Delivery getDeliveryById(Long deliveryId);

    public Delivery createNewDelivery(CreateDeliveryDto createDeliveryDto);

    public void updateDelivery(Long deliveryId, UpdateDeliveryDto updateDeliveryDto);

    public void deleteDelivery(Long deliveryId);

    public void updateDeliveryWithDriver(Long deliveryId, UUID driverId);

    
}
