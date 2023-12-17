package com.example.eventsystem.EventManagementSubsystem.entity;

import com.example.eventsystem.EventManagementSubsystem.model.StatusEnum;
import com.example.eventsystem.TicketManagementSubsystem.entity.Area;
import com.example.eventsystem.TicketManagementSubsystem.entity.Seat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "STATUTES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Status implements Serializable {
    @EmbeddedId
    private StatusPK statusPK;

    @ManyToOne(cascade = {CascadeType.ALL})
    @MapsId("eventCode")
    @JoinColumn(name = "EVENT_CODE")
    private Event event;

    @ManyToOne(cascade = {CascadeType.ALL})
    @MapsId("areaName")
    @JoinColumn(name = "AREA_NAME")
    private Area area;

    @ManyToOne(cascade = {CascadeType.ALL})
    @MapsId("seatCode")
    @JoinColumn(name = "SEAT_CODE")
    private Seat seat;

    @Column(name = "STATUS",nullable = false)
    private StatusEnum statusEnum = StatusEnum.AVAILABLE;
}
