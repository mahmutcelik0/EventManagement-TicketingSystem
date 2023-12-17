package com.example.eventsystem.TicketManagementSubsystem.service;

import com.example.eventsystem.SystemConfigSubsystem.exception.NotFoundException;
import com.example.eventsystem.TicketManagementSubsystem.dto.AreaDto;
import com.example.eventsystem.TicketManagementSubsystem.populator.AreaPopulator;
import com.example.eventsystem.TicketManagementSubsystem.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class AreaService {
    @Autowired
    public AreaRepository areaRepository;

    @Autowired
    public AreaPopulator areaPopulator;

    @Autowired
    public SeatService seatService;

    public List<AreaDto> getAllAreas() throws NotFoundException {
        if(CollectionUtils.isEmpty(areaRepository.findAll())) throw new NotFoundException("Area is empty");
        return areaPopulator.populateAll(areaRepository.findAll());
    }


    public ResponseEntity<?> addNewArea(AreaDto areaDto) {
        if(areaRepository.findAreaByAreaName(areaDto.getAreaName()).isPresent()){
            return new ResponseEntity<>("Area mevcutta var", HttpStatus.NOT_ACCEPTABLE);
        }
        else {
            var area = areaPopulator.reversePopulator(areaDto);
            seatService.saveAllSeatsofArea(area);

            areaRepository.save(area);

            return new ResponseEntity<>("Area ve Seat ler kaydedildi",HttpStatus.OK);
        }
    }
}
