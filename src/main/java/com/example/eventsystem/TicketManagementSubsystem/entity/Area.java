package com.example.eventsystem.TicketManagementSubsystem.entity;

import com.example.eventsystem.EventManagementSubsystem.entity.Event;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "AREAS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Area {
    @Id
    @Column(name = "AREA_NAME")
    private String areaName;

    @OneToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},mappedBy = "area")
    private List<Seat> seats;

    @ManyToMany(mappedBy = "areas")
    private Set<Event> events;

}
