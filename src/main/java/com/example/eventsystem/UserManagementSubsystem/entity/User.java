package com.example.eventsystem.UserManagementSubsystem.entity;


import com.example.eventsystem.SystemConfigSubsystem.entity.BaseClass;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "USERS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FIRST_NAME",nullable = false)
    private String firstName;

    private String middleName;

    @Column(name = "LAST_NAME",nullable = false)
    private String lastName;

    @Column(name = "USER_EMAIL",nullable = false)
    private String email;

    @OneToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch = FetchType.LAZY,mappedBy = "user")
    private List<Card> userCards;
}

