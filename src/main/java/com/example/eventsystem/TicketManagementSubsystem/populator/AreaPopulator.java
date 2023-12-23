package com.example.eventsystem.TicketManagementSubsystem.populator;

import com.example.eventsystem.SystemConfigSubsystem.populator.GenericPopulator;
import com.example.eventsystem.TicketManagementSubsystem.dto.AreaDto;
import com.example.eventsystem.TicketManagementSubsystem.entity.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AreaPopulator extends GenericPopulator<Area, AreaDto> {
    @Autowired
    private SeatPopulator seatPopulator;

    @Override
    public AreaDto populate(Area area) {
        return new AreaDto(area.getAreaName(),seatPopulator.populateAll(area.getSeats()));
    }

    public Area reversePopulator(AreaDto areaDto){
        Area area = new Area();

        area.setAreaName(areaDto.getAreaName());
        area.setSeats(areaDto.getSeats().stream().map(e -> seatPopulator.reversePopulator(e)).toList());
        return area;
    }


}
