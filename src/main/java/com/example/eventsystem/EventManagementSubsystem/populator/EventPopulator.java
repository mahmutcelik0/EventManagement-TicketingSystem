package com.example.eventsystem.EventManagementSubsystem.populator;

import com.example.eventsystem.EventManagementSubsystem.dto.EventDto;
import com.example.eventsystem.EventManagementSubsystem.entity.Event;
import com.example.eventsystem.EventManagementSubsystem.repository.EventRepository;
import com.example.eventsystem.SystemConfigSubsystem.populator.GenericPopulator;
import com.example.eventsystem.TicketManagementSubsystem.populator.AreaPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Component
public class EventPopulator extends GenericPopulator<Event, EventDto> {
    @Autowired
    private CategoryPopulator categoryPopulator;

    @Autowired
    private AreaPopulator areaPopulator;

    @Autowired
    private EventRepository eventRepository;

    @Override
    public EventDto populate(Event event) {
        EventDto eventDto = new EventDto();
        eventDto.setEventCode(event.getEventCode());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");

        event.getEventDate().forEach(e->{
            LocalDateTime dateTime = LocalDateTime.parse(e.getEventDate().toString(), formatter);
            if(CollectionUtils.isEmpty(eventDto.getEventDate())) eventDto.setEventDate(new ArrayList<>());
            eventDto.getEventDate().add(String.valueOf(dateTime.toLocalDate()));
            if(CollectionUtils.isEmpty(eventDto.getEventTime())) eventDto.setEventTime(new ArrayList<>());
            eventDto.getEventTime().add(String.valueOf(dateTime.toLocalTime()));
        });

        eventDto.setEventName(event.getEventName());
        eventDto.setEventDescription(event.getEventDescription());
        eventDto.setEventPrice(event.getEventPrice());
        eventDto.setCategory(categoryPopulator.populate(event.getCategory()));
        eventDto.setAreas(areaPopulator.populateAll(event.getAreas().stream().toList()));

        return eventDto;
    }

    //aa
    public Event reversePopulate(EventDto eventDto){
        return eventRepository.findEventByEventCode(eventDto.getEventCode());
    }
}
