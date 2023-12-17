package com.example.eventsystem.TicketManagementSubsystem.repository;

import com.example.eventsystem.TicketManagementSubsystem.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat,String> {
}
