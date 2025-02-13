package com.thais.mscartoes.application.services;

import com.thais.mscartoes.domain.models.Card;
import com.thais.mscartoes.domain.repositories.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    public Card save(Card card){
        return cardRepository.save(card);
    }

    public List<Card> getCardsThatIncomeLessOrEqual(Long income){
        var incomeBigDecimal = BigDecimal.valueOf(income);
        return cardRepository.findByIncomeLessThanEqual(incomeBigDecimal);
    }
}
