package com.example.eventsystem.TicketManagementSubsystem.entity;


import com.example.eventsystem.SystemConfigSubsystem.entity.BaseClass;
import com.example.eventsystem.UserManagementSubsystem.entity.Card;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "PAYMENTS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment extends BaseClass implements Serializable {
    @Column(name = "PAYMENT_DATE",nullable = false)
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private Card card;

    @OneToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name ="TICKET_CODE" ,referencedColumnName = "TICKET_CODE",nullable = false)
    private Ticket ticket;
}