package com.example.eventsystem.EventManagementSubsystem.entity;

import com.example.eventsystem.TicketManagementSubsystem.entity.Area;
import com.example.eventsystem.TicketManagementSubsystem.entity.Ticket;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "EVENTS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Event implements Serializable{
    @Id
    @Column(name = "EVENT_CODE")
    private String eventCode;

    private String eventName;

    private String eventDescription;

    @OneToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},mappedBy = "event")
    private List<EventDates> eventDate;

    private Long eventPrice;

    @OneToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},mappedBy = "event")
    private List<Ticket> tickets;

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch = FetchType.LAZY)
    @JoinTable(
            name = "EVENT_AREAS",
            joinColumns = @JoinColumn(referencedColumnName = "EVENT_CODE",name = "EVENT_CODE"),
            inverseJoinColumns = @JoinColumn(referencedColumnName = "AREA_NAME",name = "AREA_NAME")
    )
    private Set<Area> areas;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_NAME")
    @JsonBackReference
    private Category category;

    @OneToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},mappedBy = "event")
    private List<Status> statusPK;

}
