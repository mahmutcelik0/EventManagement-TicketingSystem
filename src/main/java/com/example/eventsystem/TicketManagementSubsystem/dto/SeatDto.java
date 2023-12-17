package com.example.eventsystem.TicketManagementSubsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeatDto {
    private String seatCode; //integer olabilir
    private Character seatRow;
}
