package com.example.eventsystem.UserManagementSubsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private LocalDate birthDate;
}
