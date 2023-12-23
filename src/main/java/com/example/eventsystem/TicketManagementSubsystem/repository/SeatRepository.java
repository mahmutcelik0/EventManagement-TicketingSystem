package com.example.eventsystem.TicketManagementSubsystem.repository;

import com.example.eventsystem.TicketManagementSubsystem.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat,String> {
    @Query("select s from Seat as s where s.area.areaName = ?1")
    List<Seat> getAllSeatsOfArea(String areaName);
}
