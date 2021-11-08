package com.ss.scrumptious_drivers.dao;

import com.ss.scrumptious.common_entities.entity.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}