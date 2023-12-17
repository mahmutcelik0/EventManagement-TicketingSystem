package com.example.eventsystem.TicketManagementSubsystem.repository;

import com.example.eventsystem.TicketManagementSubsystem.entity.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AreaRepository extends JpaRepository<Area,String> {
    Optional<Area> findAreaByAreaName(String areaName);
}
