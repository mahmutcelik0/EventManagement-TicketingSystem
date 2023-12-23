package com.example.eventsystem.TicketManagementSubsystem.populator;

import com.example.eventsystem.EventManagementSubsystem.populator.EventPopulator;
import com.example.eventsystem.SystemConfigSubsystem.populator.GenericPopulator;
import com.example.eventsystem.TicketManagementSubsystem.dto.TicketDto;
import com.example.eventsystem.TicketManagementSubsystem.entity.Area;
import com.example.eventsystem.TicketManagementSubsystem.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketPopulator extends GenericPopulator<Ticket, TicketDto> {
    @Autowired
    private SeatPopulator seatPopulator;

    @Autowired
    private EventPopulator eventPopulator;

    @Override
    protected TicketDto populate(Ticket ticket) {
        return null;
    }


    public Ticket reversePopulator(TicketDto ticketDto) {
        Ticket ticket = new Ticket();
        ticket.setTicketCode(ticketDto.getEvent().getEventCode() + "/" + ticketDto.getSeat().getAreaName()+"/"+ticketDto.getSeat().getSeatCode());
        ticket.setSeat(seatPopulator.reversePopulator(ticketDto.getSeat()));
        Area area = new Area();
        area.setAreaName(ticketDto.getSeat().getAreaName());
        ticket.getSeat().setArea(area);
        ticket.setEvent(eventPopulator.reversePopulate(ticketDto.getEvent()));
        return ticket;
    }
}
