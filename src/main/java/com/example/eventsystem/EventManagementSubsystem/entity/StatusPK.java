package com.example.eventsystem.EventManagementSubsystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StatusPK implements Serializable {
    @Column(name = "EVENT_CODE",nullable = false)
    private String eventCode;

    @Column(name = "AREA_NAME",nullable = false)
    private String areaName;

    @Column(name = "SEAT_CODE",nullable = false)
    private String seatCode;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        StatusPK statusPK = (StatusPK) object;
        return Objects.equals(eventCode, statusPK.eventCode) && Objects.equals(areaName, statusPK.areaName) && Objects.equals(seatCode, statusPK.seatCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventCode, areaName, seatCode);
    }
}
