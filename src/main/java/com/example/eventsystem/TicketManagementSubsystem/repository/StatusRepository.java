package com.example.eventsystem.TicketManagementSubsystem.repository;

import com.example.eventsystem.EventManagementSubsystem.entity.Status;
import com.example.eventsystem.EventManagementSubsystem.entity.StatusPK;
import com.example.eventsystem.TicketManagementSubsystem.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface StatusRepository extends JpaRepository<Status, StatusPK> {
    @Query("select st from Status as st left join Seat as s on(s.seatCode = st.seat.seatCode) left join EventDates as ed on(st.eventDates.id = ed.id) where st.event.eventCode = ?1 and ed.eventDate= ?2 and st.area.areaName = ?3")
    List<Status> getAllSeatsWithCurrentStatus(String eventCode, Date date, String areaName);
}
