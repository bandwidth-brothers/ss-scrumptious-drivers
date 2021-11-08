package com.ss.scrumptious_drivers.service;

import java.util.List;
import java.util.UUID;

import com.ss.scrumptious.common_entities.entity.Address;
import com.ss.scrumptious.common_entities.entity.Delivery;
import com.ss.scrumptious.common_entities.entity.Driver;
import com.ss.scrumptious.common_entities.entity.Order;
import com.ss.scrumptious_drivers.dao.AddressRepository;
import com.ss.scrumptious_drivers.dao.DeliveryRepository;
import com.ss.scrumptious_drivers.dao.DriverRepository;
import com.ss.scrumptious_drivers.dao.OrderRepository;
import com.ss.scrumptious_drivers.dto.CreateDeliveryDto;
import com.ss.scrumptious_drivers.dto.UpdateDeliveryDto;
import com.ss.scrumptious_drivers.exception.NoSuchAddressException;
import com.ss.scrumptious_drivers.exception.NoSuchDeliveryException;
import com.ss.scrumptious_drivers.exception.NoSuchDriverException;
import com.ss.scrumptious_drivers.exception.NoSuchOrderException;
import com.ss.scrumptious_drivers.mapper.DeliveryDtoMapper;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final AddressRepository addressRepository;
    private final DriverRepository driverRepository;
    private final OrderRepository orderRepository;
    
    @Override
    public List<Delivery> getAllDeliveries() {
        log.trace("getAllDeliveries");
        return deliveryRepository.findAll();
    }

    @Override
    public List<Delivery> getDeliveriesByDriverId(UUID driverId) {
        log.trace("getDeliveriesByDriverId = " + driverId);
        Driver driver = Driver.builder()
            .id(driverId)
            .build();

        return deliveryRepository.findByDriver(driver);
    }

    @Override
    public List<Delivery> getAllOpenDeliveries() {
        log.trace("getAllOpenDeliveries");

        return deliveryRepository.findAllOpen();
    }

    @Override
    public Delivery createNewDelivery(CreateDeliveryDto createDeliveryDto) {
        log.trace("createNewDelivery");

        Delivery delivery = DeliveryDtoMapper.map(createDeliveryDto);

        Address address = addressRepository.findById(createDeliveryDto.getAddressId())
            .orElseThrow(() -> new NoSuchAddressException(createDeliveryDto.getAddressId()));
        delivery.setAddress(address);

        Driver driver = driverRepository.findById(createDeliveryDto.getDriverId())
            .orElseThrow(() -> new NoSuchDriverException(createDeliveryDto.getDriverId()));
        delivery.setDriver(driver);

        Order order = orderRepository.findById(createDeliveryDto.getOrderId())
            .orElseThrow(() -> new NoSuchOrderException(createDeliveryDto.getOrderId()));
        delivery.setOrder(order);

        return deliveryRepository.saveAndFlush(delivery);
    }

    @Override
    public Delivery getDeliveryById(Long deliveryId) {
        log.trace("getDeliveryById deliveryId = " + deliveryId);
        return deliveryRepository.findById(deliveryId)
            .orElseThrow(() -> new NoSuchDeliveryException(deliveryId));
    }

    @Override
    public void updateDelivery(Long deliveryId, UpdateDeliveryDto updateDeliveryDto) {
        log.trace("updateDelivery deliveryId = " + deliveryId);
        Delivery delivery = getDeliveryById(deliveryId);

        delivery = DeliveryDtoMapper.map(delivery, updateDeliveryDto);

        if (updateDeliveryDto.getAddressId() != null) {
            Address address = addressRepository.findById(updateDeliveryDto.getAddressId())
                .orElseThrow(() -> new NoSuchAddressException(updateDeliveryDto.getAddressId()));
            delivery.setAddress(address);
        }

        if (updateDeliveryDto.getDriverId() != null) {
            Driver driver = driverRepository.findById(updateDeliveryDto.getDriverId())
                .orElseThrow(() -> new NoSuchDriverException(updateDeliveryDto.getDriverId()));
            delivery.setDriver(driver);
        }

        if (updateDeliveryDto.getOrderId() != null) {
             Order order = orderRepository.findById(updateDeliveryDto.getOrderId())
                .orElseThrow(() -> new NoSuchOrderException(updateDeliveryDto.getOrderId()));
            delivery.setOrder(order);
        }
       
        deliveryRepository.saveAndFlush(delivery);
    }

    @Override
    public void deleteDelivery(Long deliveryId) {
        log.trace("deleteDelivery deliveryId = " + deliveryId);

        deliveryRepository.findById(deliveryId).ifPresent(deliveryRepository::delete);
    }

    @Override
    public void updateDeliveryWithDriver(Long deliveryId, UUID driverId) {
        log.trace("updateDeliveryWithDriver deliveryId = " + deliveryId + ", driverId = " + driverId);

        Delivery delivery = getDeliveryById(deliveryId);
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new NoSuchDriverException(driverId));
        delivery.setDriver(driver);
        delivery.setDeliveryStatus("DRIVER ASSIGNED");

        deliveryRepository.saveAndFlush(delivery);
    }
    
}
