package com.example.eventsystem.EventManagementSubsystem.entity;

import com.example.eventsystem.TicketManagementSubsystem.entity.Area;
import com.example.eventsystem.TicketManagementSubsystem.entity.Ticket;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "EVENTS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @Column(name = "EVENT_CODE")
    private String eventCode;

    private String eventName;

    private String eventDescription;

    private Date eventDate;

    @OneToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},mappedBy = "event")
    private List<Ticket> tickets;

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(
            name = "EVENT_AREAS",
            joinColumns = @JoinColumn(referencedColumnName = "EVENT_CODE",name = "EVENT_CODE"),
            inverseJoinColumns = @JoinColumn(referencedColumnName = "AREA_NAME",name = "AREA_NAME")
    )
    private Set<Area> areas;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private Category category;

    @OneToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},mappedBy = "event")
    private List<Status> statusPK;
}
