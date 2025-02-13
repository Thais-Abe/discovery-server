package com.thais.mscartoes.application.controllers;

import com.thais.mscartoes.application.services.CardService;
import com.thais.mscartoes.domain.models.Card;
import com.thais.mscartoes.domain.models.dtos.CardSaveRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cartoes")
@RequiredArgsConstructor
public class CardsResource {

    private final CardService service;
    @GetMapping
    public String status(){
        return "Ok";
    }
    @PostMapping
    public ResponseEntity register(@RequestBody CardSaveRequestDTO request){
        Card card = request.cardDTOToCard();
        service.save(card);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping(params = "income")
    public ResponseEntity<List<Card>> getCardsIncomeUntil(@RequestParam("income") Long income){
      return ResponseEntity.ok(service.getCardsThatIncomeLessOrEqual(income));
    }
}
