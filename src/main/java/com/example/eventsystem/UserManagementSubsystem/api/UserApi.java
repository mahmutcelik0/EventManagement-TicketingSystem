package com.example.eventsystem.UserManagementSubsystem.api;

import com.example.eventsystem.SystemConfigSubsystem.exception.NotFoundException;
import com.example.eventsystem.UserManagementSubsystem.dto.CardDto;
import com.example.eventsystem.UserManagementSubsystem.dto.UserDto;
import com.example.eventsystem.UserManagementSubsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserApi {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers() throws NotFoundException {
        return userService.getAllUsers();
    }

    @PostMapping("/login")
    public void login(){
        // Bu aşamada herhangi bir şeye gerek yok
    }

    @GetMapping("/cards")
    public List<CardDto> getAllCardsOfUser(@RequestParam String email) throws NotFoundException {
        return userService.getAllCardsOfUser(email);
    }

    @PostMapping("/cards")
    public ResponseEntity<?> addNewCardToUser(@RequestParam String email, @RequestBody CardDto cardDto) throws NotFoundException {
        return userService.addNewCardToUser(email,cardDto);
    }



}
