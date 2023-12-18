package com.example.eventsystem.UserManagementSubsystem.entity;

import com.example.eventsystem.SystemConfigSubsystem.entity.BaseClass;
import com.example.eventsystem.TicketManagementSubsystem.entity.Payment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.YearMonth;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "CARDS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Card extends BaseClass implements Serializable {
    @Column(name = "CARD_NAME", nullable = false)
    private String cardName;

    @Column(name = "CARD_NUMBER",nullable = false) //encrypt edilecek
    private String cardNumber;

    @Column(name = "CARD_EXPIRATION_DATE",nullable = false)
    private YearMonth cardExpirationDate;

    @Column(name = "CARD_CVV_CODE",nullable = false,length = 3)
    private Integer cardCvv;

    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "card", fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private List<Payment> payments;
}
