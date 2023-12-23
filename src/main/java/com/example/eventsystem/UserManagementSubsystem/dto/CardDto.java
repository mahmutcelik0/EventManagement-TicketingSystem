package com.example.eventsystem.UserManagementSubsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.YearMonth;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardDto {
    private String cardName;
    private Long cardNumber;
    private YearMonth cardExpirationDate;
    private Integer cardCvv;
    private Long balance;
}
