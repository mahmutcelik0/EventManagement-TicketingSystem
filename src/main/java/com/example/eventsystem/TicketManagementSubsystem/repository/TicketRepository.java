package com.example.eventsystem.TicketManagementSubsystem.repository;

import com.example.eventsystem.TicketManagementSubsystem.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,String> {
    @Query("select t from Ticket as t where t.event.eventCode = ?1 and t.seat.seatCode = ?2")
    Optional<Ticket> getTicket(String eventCode,String seatCode);

}
