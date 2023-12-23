package com.example.eventsystem.EventManagementSubsystem.dto;

import com.example.eventsystem.TicketManagementSubsystem.dto.AreaDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
    private String eventCode;
    private String eventName;
    private String eventDescription;
    private Date eventDate;
    private Long eventPrice;
    private CategoryDto category;
    private List<AreaDto> areas;
}
