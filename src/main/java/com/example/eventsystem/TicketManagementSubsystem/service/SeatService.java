package com.example.eventsystem.TicketManagementSubsystem.service;

import com.example.eventsystem.TicketManagementSubsystem.dto.SeatDto;
import com.example.eventsystem.TicketManagementSubsystem.entity.Area;
import com.example.eventsystem.TicketManagementSubsystem.populator.SeatPopulator;
import com.example.eventsystem.TicketManagementSubsystem.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public class SeatService {
    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private SeatPopulator seatPopulator;

    public void saveAllSeatsofArea(Area area) {
        area.getSeats().forEach(e -> {
            e.setArea(area);
            seatRepository.save(e);
        });
    }

    public List<SeatDto> getAllSeatsOfArea(String areaName) {
        var seatsOfArea = seatRepository.getAllSeatsOfArea(areaName);
        if(CollectionUtils.isEmpty(seatsOfArea)) throw new NotFoundException("There isn't any seats found for given area");
        return seatPopulator.populateAll(seatsOfArea);
    }
}
