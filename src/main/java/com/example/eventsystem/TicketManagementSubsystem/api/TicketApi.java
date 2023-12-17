package com.example.eventsystem.TicketManagementSubsystem.api;

import com.example.eventsystem.SystemConfigSubsystem.exception.NotFoundException;
import com.example.eventsystem.TicketManagementSubsystem.dto.AreaDto;
import com.example.eventsystem.TicketManagementSubsystem.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketApi {
    @Autowired
    private AreaService areaService;


    @GetMapping("/areas")
    public List<AreaDto> getAllAreas() throws NotFoundException {
        return areaService.getAllAreas();
    }

    @PostMapping("/areas")
    public ResponseEntity<?> addNewArea(@RequestBody AreaDto areaDto){
        return areaService.addNewArea(areaDto);
    }

}
