package com.example.eventsystem.TicketManagementSubsystem.service;

import com.example.eventsystem.EventManagementSubsystem.entity.Status;
import com.example.eventsystem.EventManagementSubsystem.entity.StatusDto;
import com.example.eventsystem.TicketManagementSubsystem.dto.SeatDto;
import com.example.eventsystem.TicketManagementSubsystem.entity.Area;
import com.example.eventsystem.TicketManagementSubsystem.populator.SeatPopulator;
import com.example.eventsystem.TicketManagementSubsystem.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.webjars.NotFoundException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SeatService {
    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private SeatPopulator seatPopulator;

    @Autowired
    private StatusService statusService;

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

    public List<?> getAllSeatsWithCurrentStatus(String eventCode,String eventDate,String eventTime,String areaName){
        String dateTimeString = "2024" + " " + eventDate + " " + eventTime;

        // Define the date-time formatter for parsing
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy dd MMM HH:mm");

        // Parse the combined date and time string
        LocalDateTime localDateTime = LocalDateTime.parse(dateTimeString, formatter);

        // Convert LocalDateTime to Date
        Date date = java.sql.Timestamp.valueOf(localDateTime);

        List<Status> seats = statusService.getAllSeatsWithCurrentStatus(eventCode,date,areaName);

        var status = new ArrayList<>();
        seats.forEach(e->{
            StatusDto statusDto = new StatusDto();
            statusDto.setEventCode(e.getEvent().getEventCode());
            statusDto.setSeatCode(e.getSeat().getSeatCode());
            statusDto.setStatus(e.getStatusEnum().name());
            status.add(statusDto);
        });


        return status;
    }
}
