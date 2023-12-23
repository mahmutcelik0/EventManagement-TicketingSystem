package com.example.eventsystem.EventManagementSubsystem.populator;

import com.example.eventsystem.EventManagementSubsystem.dto.EventDto;
import com.example.eventsystem.EventManagementSubsystem.entity.Event;
import com.example.eventsystem.EventManagementSubsystem.repository.EventRepository;
import com.example.eventsystem.SystemConfigSubsystem.populator.GenericPopulator;
import com.example.eventsystem.TicketManagementSubsystem.populator.AreaPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        eventDto.setEventDate(event.getEventDate());
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
