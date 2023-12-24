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
@Table(name = "STATUTES",uniqueConstraints =  @UniqueConstraint(columnNames = {"event_dates_id", "EVENT_CODE","AREA_NAME","SEAT_CODE"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Status implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "EVENT_CODE",referencedColumnName = "EVENT_CODE")
    private Event event;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "AREA_NAME",referencedColumnName = "AREA_NAME")
    private Area area;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "SEAT_CODE",referencedColumnName = "SEAT_CODE")
    private Seat seat;

    @Column(name = "STATUS",nullable = false)
    private StatusEnum statusEnum = StatusEnum.AVAILABLE;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "event_dates_id", referencedColumnName = "id",unique = false)
    private EventDates eventDates;
}
