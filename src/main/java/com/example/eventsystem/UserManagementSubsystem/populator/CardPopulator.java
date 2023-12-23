package com.example.eventsystem.UserManagementSubsystem.populator;

import com.example.eventsystem.SystemConfigSubsystem.populator.GenericPopulator;
import com.example.eventsystem.UserManagementSubsystem.dto.CardDto;
import com.example.eventsystem.UserManagementSubsystem.entity.Card;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CardPopulator extends GenericPopulator<Card, CardDto> {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public CardDto populate(Card card) {
        CardDto cardDto = new CardDto();
        cardDto.setCardCvv(card.getCardCvv());
        cardDto.setCardName(card.getCardName());
        cardDto.setCardExpirationDate(card.getCardExpirationDate());
        if(ObjectUtils.isNotEmpty(card.getBalance())) cardDto.setBalance(card.getBalance());
        return cardDto;
    }


    public Card reversePopulator(CardDto cardDto) {
        Card card = new Card();
        card.setCardCvv(cardDto.getCardCvv());
        card.setCardName(cardDto.getCardName());
        card.setCardNumber(passwordEncoder.encode(String.valueOf(cardDto.getCardNumber())));
        card.setCardExpirationDate(cardDto.getCardExpirationDate());
        card.setBalance(cardDto.getBalance());
        return card;
    }
}