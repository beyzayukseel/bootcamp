package org.com.thy.bootcamp.service;

import lombok.RequiredArgsConstructor;
import org.com.thy.bootcamp.entity.Card;
import org.com.thy.bootcamp.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CardService {

    private final CardRepository cardRepository;


    public Card createCard(Card card) {
        return cardRepository.save(card);
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
