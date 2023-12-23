package com.example.eventsystem.EventManagementSubsystem.repository;

import com.example.eventsystem.EventManagementSubsystem.entity.Event;
import com.example.eventsystem.EventManagementSubsystem.model.StatusEnum;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event,String> {
    Event findEventByEventCode(String eventCode);

    @Query("select e from Event as e where e.category.categoryName = ?1")
    List<Event> getAllEventsOfCategories(String categoryName);

    @Query("select s.statusEnum from Event as e left join Status as s on(e.eventCode = s.event.eventCode) where e.eventCode = ?1 and s.area.areaName = ?2 and s.seat.seatCode = ?3")
    StatusEnum isReserved(String eventCode, String areaName,String seatCode);

    @Transactional
    @Modifying
    @Query("update Status set statusEnum = ?1 where area.areaName = ?2 and event.eventCode = ?3 and seat.seatCode = ?4")
    void updateSeatStatus(StatusEnum status, String areaName,String eventCode,String seatCode);
}
