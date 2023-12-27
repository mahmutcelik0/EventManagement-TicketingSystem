package com.example.eventsystem.TicketManagementSubsystem.api;

import com.example.eventsystem.EventManagementSubsystem.dto.EventDto;
import com.example.eventsystem.SystemConfigSubsystem.exception.InsufficientBalanceException;
import com.example.eventsystem.SystemConfigSubsystem.exception.NotFoundException;
import com.example.eventsystem.TicketManagementSubsystem.dto.AreaDto;
import com.example.eventsystem.TicketManagementSubsystem.dto.PaymentDto;
import com.example.eventsystem.TicketManagementSubsystem.dto.SeatDto;
import com.example.eventsystem.TicketManagementSubsystem.dto.TicketDto;
import com.example.eventsystem.TicketManagementSubsystem.service.AreaService;
import com.example.eventsystem.TicketManagementSubsystem.service.PaymentService;
import com.example.eventsystem.TicketManagementSubsystem.service.SeatService;
import com.example.eventsystem.UserManagementSubsystem.dto.CardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Date;
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
    public ResponseEntity<?> addNewArea(@RequestBody AreaDto areaDto) {
        return areaService.addNewArea(areaDto);
    }

    @GetMapping("/payments/getAll")
    public List<PaymentDto> getAllPaymentsOfUser(@RequestParam String email) throws NotFoundException {
        var payments = paymentService.getAllPaymentsOfUser(email);
        if (CollectionUtils.isEmpty(payments)) throw new NotFoundException("User doesn't have any payment");
        return payments;
    }

    @GetMapping(value = "/payments", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<?> addNewPaymentToUser(@RequestParam("email") String email,
                                                 @RequestParam("eventDate") String eventDate,
                                                 @RequestParam("eventTime") String eventTime,
                                                 @RequestParam("cardNumber") String cardNumber,
                                                 @RequestParam("cardMonth") String cardMonth,
                                                 @RequestParam("cardYear") String cardYear,
                                                 @RequestParam("cardCvv") String cardCvv,
                                                 @RequestParam("seatCode") String seatCode,
                                                 @RequestParam("eventCode") String eventCode) throws Exception {
        cardYear = cardYear.isEmpty() ?"24":cardYear;
        YearMonth yearMonth = YearMonth.parse("20"+cardYear+"-"+cardMonth);

        CardDto cardDto = new CardDto(Long.valueOf(cardNumber),yearMonth,Integer.valueOf(cardCvv));
        SeatDto seatDto = new SeatDto(seatCode,"Salon-2");
        EventDto eventDto = new EventDto(eventCode);
        TicketDto ticketDto = new TicketDto(seatDto,eventDto);
        PaymentDto paymentDto = new PaymentDto(cardDto,ticketDto);

        return paymentService.addNewPaymentToUser(email, paymentDto, eventDate, eventTime);
    }

    @GetMapping("/seats/{area}")
    public List<SeatDto> getAllSeatsOfArea(@PathVariable("area") String areaName) {
        return seatService.getAllSeatsOfArea(areaName);
    }

    @GetMapping("/seats")
    public List<?> getAllSeatsWithCurrentStatus(String eventCode, String eventDate, String eventTime, String areaName) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd%20MMM");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMM");

        try {
            Date date = inputFormat.parse(eventDate);
            String outputDate = outputFormat.format(date);
            return seatService.getAllSeatsWithCurrentStatus(eventCode, outputDate, eventTime, areaName);

        } catch (ParseException e) {
            throw new RuntimeException("");
        }
    }


}