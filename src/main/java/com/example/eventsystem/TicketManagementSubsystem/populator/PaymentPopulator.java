package com.example.eventsystem.TicketManagementSubsystem.populator;

import com.example.eventsystem.SystemConfigSubsystem.populator.GenericPopulator;
import com.example.eventsystem.TicketManagementSubsystem.dto.PaymentDto;
import com.example.eventsystem.TicketManagementSubsystem.entity.Payment;
import com.example.eventsystem.UserManagementSubsystem.populator.CardPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PaymentPopulator extends GenericPopulator<Payment, PaymentDto> {
    @Autowired
    private CardPopulator cardPopulator;

    @Autowired
    private TicketPopulator ticketPopulator;

    @Override
    protected PaymentDto populate(Payment payment) {
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setDate(payment.getDate());
        paymentDto.setCard(cardPopulator.populate(payment.getCard()));
        paymentDto.setTicket(ticketPopulator.populate(payment.getTicket()));
        return paymentDto;
    }

    public Payment reversePopulator(PaymentDto paymentDto){
        Payment payment = new Payment();
        payment.setCard(cardPopulator.reversePopulator(paymentDto.getCard()));
        payment.setTicket(ticketPopulator.reversePopulator(paymentDto.getTicket()));
        payment.setDate(LocalDateTime.now());
        return payment;
    }
}
