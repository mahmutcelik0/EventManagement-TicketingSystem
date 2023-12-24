package com.example.eventsystem.TicketManagementSubsystem.service;

import com.example.eventsystem.EventManagementSubsystem.entity.Status;
import com.example.eventsystem.TicketManagementSubsystem.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StatusService {
    @Autowired
    private StatusRepository statusRepository;


    public List<Status> getAllSeatsWithCurrentStatus(String eventCode, Date date, String areaName) {
        return statusRepository.getAllSeatsWithCurrentStatus(eventCode,date,areaName);
    }
}
