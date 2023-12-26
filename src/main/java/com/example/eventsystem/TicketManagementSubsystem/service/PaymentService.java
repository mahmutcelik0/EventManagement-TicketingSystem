package com.example.eventsystem.TicketManagementSubsystem.service;

import com.example.eventsystem.EventManagementSubsystem.entity.Event;
import com.example.eventsystem.EventManagementSubsystem.service.EventService;
import com.example.eventsystem.SystemConfigSubsystem.exception.InsufficientBalanceException;
import com.example.eventsystem.SystemConfigSubsystem.exception.NotFoundException;
import com.example.eventsystem.SystemConfigSubsystem.service.QrGeneratorService;
import com.example.eventsystem.TicketManagementSubsystem.dto.PaymentDto;
import com.example.eventsystem.TicketManagementSubsystem.entity.Area;
import com.example.eventsystem.TicketManagementSubsystem.entity.Payment;
import com.example.eventsystem.TicketManagementSubsystem.populator.PaymentPopulator;
import com.example.eventsystem.TicketManagementSubsystem.repository.PaymentRepository;
import com.example.eventsystem.UserManagementSubsystem.service.CardService;
import com.example.eventsystem.UserManagementSubsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PaymentPopulator paymentPopulator;

    @Autowired
    private CardService cardService;

    @Autowired
    private EventService eventService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private QrGeneratorService qrGeneratorService;

    public List<PaymentDto> getAllPaymentsOfUser(String email) throws NotFoundException {
        userService.checkUserExistence(email);
        return paymentPopulator.populateAll(paymentRepository.getAllPaymentsOfUser(email));
    }

    public ResponseEntity<?> addNewPaymentToUser(String email, PaymentDto paymentDto, String eventDate, String eventTime) throws Exception {
        userService.checkUserExistence(email);

        var card = cardService.getCardOfUser(email, paymentDto.getCard());
        Event event = eventService.getEventByEventCode(paymentDto.getTicket().getEvent().getEventCode());

        Optional<Area> area = event.getAreas().stream().filter(e -> e.getAreaName().equalsIgnoreCase(paymentDto.getTicket().getSeat().getAreaName())).findFirst();

        Date date = refactorDate(eventDate, eventTime);

        if (area.isEmpty()) {
            throw new NotFoundException("Area does not exists");
        } else if (area.get().getSeats().stream().noneMatch(e -> e.getSeatCode().equalsIgnoreCase(paymentDto.getTicket().getSeat().getSeatCode()))) {
            throw new NotFoundException("Seat does not found in given area");
        } else if (card.getBalance() < event.getEventPrice())
            throw new InsufficientBalanceException("Insufficient balance");
        else if (!eventService.isAvailable(paymentDto.getTicket().getEvent().getEventCode(), paymentDto.getTicket().getSeat().getAreaName(), paymentDto.getTicket().getSeat().getSeatCode(), date))
            throw new RuntimeException("Seat is already reserved");
        else {
            card.setBalance(card.getBalance() - event.getEventPrice());

            var payment = new Payment();
            payment.setDate(LocalDateTime.now());
            payment.setCard(card);
            payment.setTicket(ticketService.getTicket(paymentDto.getTicket(),date));
            paymentRepository.save(payment);

            var qrCode = qrGeneratorService.generateQRCode(
                    event.getEventName(),
                    date.toString(),
                    paymentDto.getTicket().getSeat().getAreaName(),
                    paymentDto.getTicket().getSeat().getSeatCode());

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(qrCode, "png", baos);
            var qr = baos.toByteArray();

            eventService.updateSeatStatus(paymentDto.getTicket().getSeat().getAreaName(),
                    event.getEventCode(), paymentDto.getTicket().getSeat().getSeatCode(), date);

            return new ResponseEntity<>(qr, HttpStatus.OK);

        }
    }

    private Date refactorDate(String eventDate, String eventTime) {
        String dateTimeString = "2024" + " " + eventDate + " " + eventTime;

        // Define the date-time formatter for parsing
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy dd MMM HH:mm");

        // Parse the combined date and time string
        LocalDateTime localDateTime = LocalDateTime.parse(dateTimeString, formatter);

        // Convert LocalDateTime to Date
        Date date = java.sql.Timestamp.valueOf(localDateTime);
        return date;
    }
}
