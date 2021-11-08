package com.ss.scrumptious_drivers.dao;

import java.util.List;

import com.ss.scrumptious.common_entities.entity.Delivery;
import com.ss.scrumptious.common_entities.entity.Driver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    List<Delivery> findByDriver(Driver driver);

    @Query(value = "SELECT * FROM DELIVERY d WHERE d.delivery_status = 'UNASSIGNED'", nativeQuery = true)
    List<Delivery> findAllOpen();
}