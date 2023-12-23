package com.example.eventsystem.UserManagementSubsystem.service;

import com.example.eventsystem.SystemConfigSubsystem.exception.NotFoundException;
import com.example.eventsystem.UserManagementSubsystem.dto.CardDto;
import com.example.eventsystem.UserManagementSubsystem.dto.UserDto;
import com.example.eventsystem.UserManagementSubsystem.entity.User;
import com.example.eventsystem.UserManagementSubsystem.populator.UserPopulator;
import com.example.eventsystem.UserManagementSubsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserPopulator userPopulator;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CardService cardService;

    public List<UserDto> getAllUsers() throws NotFoundException {
        var users = userRepository.findAll();
        if (CollectionUtils.isEmpty(users)) throw new NotFoundException("There is no user");
        return userPopulator.populateAll(users);
    }

    public boolean authenticateUser(String email, String password) {
        Optional<User> user = userRepository.findUserByEmail(email);
        return user.filter(value -> passwordEncoder.matches(password, value.getPassword())).isPresent();
    }

    public User getUser(String email) throws NotFoundException {
        Optional<User> user = userRepository.findUserByEmail(email);
        if (user.isEmpty()) throw new NotFoundException("User does not exist");
        return user.get();
    }

    public ResponseEntity<?> addNewCardToUser(String email, CardDto cardDto) throws NotFoundException {
        return cardService.addNewCardToUser(cardDto, getUser(email));
    }

    public List<CardDto> getAllCardsOfUser(String email) throws NotFoundException {
        return cardService.getAllCardsOfUser(getUser(email).getId());
    }

    public boolean checkUserExistence(String email){
        return userRepository.findUserByEmail(email).isPresent();
    }
}
