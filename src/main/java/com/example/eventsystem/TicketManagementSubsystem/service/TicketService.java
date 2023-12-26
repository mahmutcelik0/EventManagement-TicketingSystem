package com.example.eventsystem.TicketManagementSubsystem.service;

import com.example.eventsystem.TicketManagementSubsystem.dto.TicketDto;
import com.example.eventsystem.TicketManagementSubsystem.entity.Ticket;
import com.example.eventsystem.TicketManagementSubsystem.populator.TicketPopulator;
import com.example.eventsystem.TicketManagementSubsystem.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketPopulator ticketPopulator;

    //
    public Ticket makeReservationAndGetTicket(String email, TicketDto ticket) {
        //todo - SEAT AYARLANACAK bozuyor
        var newTicket = ticketPopulator.reversePopulator(ticket);
        //event areas problemi var
        //newTicket.getEvent().setAreas(null);
        return ticketRepository.save(newTicket);


    }

    public Ticket getTicket(TicketDto ticketDto, Date date) {
        return ticketRepository.getTicket(ticketDto.getEvent().getEventCode(),ticketDto.getSeat().getSeatCode(),date).get();
    }
}
