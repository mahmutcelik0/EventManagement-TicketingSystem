package com.example.eventsystem.EventManagementSubsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
    private String eventCode;

    private String eventName;

    private String eventDescription;

    private Date eventDate;


}
