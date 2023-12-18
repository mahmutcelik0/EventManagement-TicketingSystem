package com.example.eventsystem.UserManagementSubsystem.service;

import com.example.eventsystem.UserManagementSubsystem.dto.CardDto;
import com.example.eventsystem.UserManagementSubsystem.entity.Card;
import com.example.eventsystem.UserManagementSubsystem.entity.User;
import com.example.eventsystem.UserManagementSubsystem.populator.CardPopulator;
import com.example.eventsystem.UserManagementSubsystem.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardPopulator cardPopulator;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<?> addNewCardToUser(CardDto cardDto, User user) {
        var card = cardPopulator.reversePopulator(cardDto);
        card.setUser(user);
        Optional<Card> exist = cardRepository.findAll().stream().filter(e -> passwordEncoder.matches(String.valueOf(cardDto.getCardNumber()), e.getCardNumber())).findFirst();

        if (exist.isPresent()) {
            return new ResponseEntity<>("Card number already exists", HttpStatus.NOT_ACCEPTABLE);
        } else {
            cardRepository.save(card);
            return new ResponseEntity<>("Card saved successfully", HttpStatus.OK);
        }
    }

    public List<CardDto> getAllCardsOfUser(Long userId) {
        return cardPopulator.populateAll(cardRepository.findAllByUserId(userId));
    }
}
