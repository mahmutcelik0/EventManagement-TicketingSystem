package com.example.eventsystem.TicketManagementSubsystem.dto;

import com.example.eventsystem.UserManagementSubsystem.dto.CardDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private LocalDateTime date;
    private CardDto card;
    private TicketDto ticket;
}
