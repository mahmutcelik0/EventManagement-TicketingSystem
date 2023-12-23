package com.example.eventsystem.TicketManagementSubsystem.dto;


import com.example.eventsystem.EventManagementSubsystem.dto.EventDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {
    private SeatDto seat; //area olması gerekli
    private EventDto event;
}
