package com.example.eventsystem.TicketManagementSubsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "SEATS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Seat implements Serializable {
    @Id
    @Column(name = "SEAT_CODE",nullable = false)
    private String seatCode;

    @Column(name = "SEAT_ROW")
    private Character seatRow;

//    @OneToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},mappedBy = "seat")

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "AREA_NAME")
    @JsonIgnore
    private Area area;

}
