package com.example.eventsystem.TicketManagementSubsystem.api;

import com.example.eventsystem.SystemConfigSubsystem.exception.InsufficientBalanceException;
import com.example.eventsystem.SystemConfigSubsystem.exception.NotFoundException;
import com.example.eventsystem.TicketManagementSubsystem.dto.AreaDto;
import com.example.eventsystem.TicketManagementSubsystem.dto.PaymentDto;
import com.example.eventsystem.TicketManagementSubsystem.dto.SeatDto;
import com.example.eventsystem.TicketManagementSubsystem.service.AreaService;
import com.example.eventsystem.TicketManagementSubsystem.service.PaymentService;
import com.example.eventsystem.TicketManagementSubsystem.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketApi {
    @Autowired
    private AreaService areaService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private SeatService seatService;

    @GetMapping("/areas")
    public List<AreaDto> getAllAreas() throws NotFoundException {
        return areaService.getAllAreas();
    }

    @PostMapping("/areas")
    public ResponseEntity<?> addNewArea(@RequestBody AreaDto areaDto){
        return areaService.addNewArea(areaDto);
    }

    @GetMapping("/payments")
    public List<PaymentDto> getAllPaymentsOfUser(@RequestParam String email) throws NotFoundException {
        var payments =  paymentService.getAllPaymentsOfUser(email);
        if(CollectionUtils.isEmpty(payments)) throw new NotFoundException("User doesn't have any payment");
        return payments;
    }

    @PostMapping(value = "/payments",produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<?> addNewPaymentToUser(@RequestParam String email, @RequestBody PaymentDto paymentDto) throws Exception {
        return paymentService.addNewPaymentToUser(email,paymentDto);
    }

    @GetMapping("/seats/{area}")
    public List<SeatDto> getAllSeatsOfArea(@PathVariable("area") String areaName){
        return seatService.getAllSeatsOfArea(areaName);
    }


}
