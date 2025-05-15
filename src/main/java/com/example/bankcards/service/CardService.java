package com.example.bankcards.service;

import com.example.bankcards.entity.Card;
import com.example.bankcards.exception.NotFoundException;
import com.example.bankcards.repository.CardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CardService {
    private final CardRepository cardRepo;

    public CardService(CardRepository cardRepo) {
        this.cardRepo = cardRepo;
    }

    public List<Card> listByAccount(Long accountId) {
        return cardRepo.findByAccountId(accountId);
    }

    public Card getById(Long id) {
        return cardRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Card not found with id: " + id));
    }

    public Card create(Card card) {
        return cardRepo.save(card);
    }

    public void delete(Long id) {
        if (!cardRepo.existsById(id)) {
            throw new NotFoundException("Card not found with id: " + id);
        }
        cardRepo.deleteById(id);
    }
}
