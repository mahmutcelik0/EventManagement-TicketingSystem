package com.example.eventsystem.TicketManagementSubsystem.service;

import com.example.eventsystem.TicketManagementSubsystem.entity.Area;
import com.example.eventsystem.TicketManagementSubsystem.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatService {
    @Autowired
    private SeatRepository seatRepository;


    public void saveAllSeatsofArea(Area area) {
        area.getSeats().forEach(e -> {
            e.setArea(area);
            seatRepository.save(e);
        });
    }
}
