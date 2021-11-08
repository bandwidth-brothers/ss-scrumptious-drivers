package com.ss.scrumptious_drivers.dao;

import java.util.UUID;

import com.ss.scrumptious.common_entities.entity.Driver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, UUID> {

}