package com.example.eventsystem.SystemConfigSubsystem.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public class BaseClass {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

}
