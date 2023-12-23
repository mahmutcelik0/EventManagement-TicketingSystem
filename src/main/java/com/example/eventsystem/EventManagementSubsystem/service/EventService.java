package com.example.eventsystem.EventManagementSubsystem.service;

import com.example.eventsystem.EventManagementSubsystem.dto.EventDto;
import com.example.eventsystem.EventManagementSubsystem.entity.Event;
import com.example.eventsystem.EventManagementSubsystem.model.StatusEnum;
import com.example.eventsystem.EventManagementSubsystem.populator.EventPopulator;
import com.example.eventsystem.EventManagementSubsystem.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.webjars.NotFoundException;

import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventPopulator eventPopulator;

    @Autowired
    private CategoryService categoryService;

    public Event getEventByEventCode(String eventCode) {
        var event =  eventRepository.findEventByEventCode(eventCode);
        if(ObjectUtils.isEmpty(event)) throw new NotFoundException("Event does not exists");
        return event;
    }

    public List<EventDto> getAllEventsOfCategories(String categoryName) {
        if(!categoryService.checkExistenceOfCategory(categoryName)) throw new NotFoundException("Category does not exists");
        var eventsOfCategory = eventRepository.getAllEventsOfCategories(categoryName);
        if(CollectionUtils.isEmpty(eventsOfCategory)) throw new NotFoundException("Category does not have any event yet.");
        //return eventPopulator.populateAll(eventsOfCategory);
        return eventPopulator.populateAll(eventsOfCategory);
    }

    public boolean isAvailable(String eventCode, String areaName, String seatCode){
        return eventRepository.isReserved(eventCode,areaName,seatCode).name().equals(StatusEnum.AVAILABLE.name());
    }

    public void updateSeatStatus(String areaName,String eventCode,String seatCode){
        eventRepository.updateSeatStatus(StatusEnum.RESERVED,areaName,eventCode,seatCode);
    }
}
