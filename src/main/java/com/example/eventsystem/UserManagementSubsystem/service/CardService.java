package com.example.eventsystem.UserManagementSubsystem.service;

import com.example.eventsystem.SystemConfigSubsystem.exception.NotFoundException;
import com.example.eventsystem.UserManagementSubsystem.dto.CardDto;
import com.example.eventsystem.UserManagementSubsystem.entity.Card;
import com.example.eventsystem.UserManagementSubsystem.entity.User;
import com.example.eventsystem.UserManagementSubsystem.populator.CardPopulator;
import com.example.eventsystem.UserManagementSubsystem.repository.CardRepository;
import org.apache.commons.lang3.ObjectUtils;
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

    public Card getCardOfUser(String email, CardDto card) throws NotFoundException {
        var userCards = cardRepository.findAllByUserEmail(email);
        var userCard = userCards.stream().filter(e -> {
            if (!e.getCardCvv().equals(card.getCardCvv())) return false;
            else if (e.getCardExpirationDate().equals(card.getCardExpirationDate())) return false;
            else if (!passwordEncoder.matches(String.valueOf(card.getCardNumber()), e.getCardNumber())) return false;
            return true;
        }).findFirst();

        if (userCard.isEmpty()) throw new NotFoundException("User card informations are incorrect");
        return userCard.get();
    }

}
