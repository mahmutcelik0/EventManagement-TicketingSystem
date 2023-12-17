package com.example.eventsystem.TicketManagementSubsystem.populator;

import com.example.eventsystem.SystemConfigSubsystem.populator.GenericPopulator;
import com.example.eventsystem.TicketManagementSubsystem.dto.SeatDto;
import com.example.eventsystem.TicketManagementSubsystem.entity.Seat;
import org.springframework.stereotype.Component;

@Component
public class SeatPopulator extends GenericPopulator<Seat, SeatDto> {
    @Override
    protected SeatDto populate(Seat seat) {
        return new SeatDto(seat.getSeatCode(),seat.getSeatRow());
    }

    protected Seat reversePopulator(SeatDto seatDto){
        Seat seat = new Seat();
        seat.setSeatCode(seatDto.getSeatCode());
        seat.setSeatRow(seatDto.getSeatRow());
        return seat;
    }
}
