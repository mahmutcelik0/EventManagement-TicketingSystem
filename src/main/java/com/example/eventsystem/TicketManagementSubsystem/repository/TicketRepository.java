package com.example.eventsystem.TicketManagementSubsystem.repository;

import com.example.eventsystem.TicketManagementSubsystem.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,String> {
}
