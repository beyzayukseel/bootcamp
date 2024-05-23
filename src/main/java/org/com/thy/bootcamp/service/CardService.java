package org.com.thy.bootcamp.service;

import lombok.RequiredArgsConstructor;
import org.com.thy.bootcamp.entity.Card;
import org.com.thy.bootcamp.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class CardService {

    private final CardRepository cardRepository;


    public Card createCard(Card card) {
        card.setCardNumber(generateUniqueCardNumber());
        return cardRepository.save(card);
    }

    public String generateUniqueCardNumber() {
        String cardNumber;
        do {
            cardNumber = generateRandomCardNumber();
        } while (cardRepository.existsByCardNumber(cardNumber));
        return cardNumber;
    }

    private String generateRandomCardNumber() {
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            cardNumber.append(random.nextInt(10));
        }
        return cardNumber.toString();
    }


    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }


    public Optional<Card> getCardById(Long id) {
        return cardRepository.findById(id);
    }


    public Optional<Card> updateCard(Long id, Card cardDetails) {
        return cardRepository.findById(id).map(card -> {
            card.setCardNumber(cardDetails.getCardNumber());
            card.setBoundary(cardDetails.getBoundary());
            card.setDebt(cardDetails.getDebt());
            card.setIsDeleted(cardDetails.getIsDeleted());
            card.setCreateDate(cardDetails.getCreateDate());
            card.setCardStatus(cardDetails.getCardStatus());
            card.setAccountList(cardDetails.getAccountList());
            return cardRepository.save(card);
        });
    }


    public boolean deleteCard(Long id) {
        if (cardRepository.existsById(id)) {
            cardRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}