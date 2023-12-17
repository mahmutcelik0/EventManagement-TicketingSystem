package com.example.eventsystem.TicketManagementSubsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AreaDto {
    private String areaName;
    private List<SeatDto> seats;
}
